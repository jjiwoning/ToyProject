package jiwon.board.service;

import jiwon.board.domain.Member;
import jiwon.board.exception.LoginFailException;
import jiwon.board.repository.BoardRepository;
import jiwon.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final EntityManager em;

    //회원 정보 조회
    public Member findMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        return member;
    }

    //로그인
    public Member login(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new LoginFailException("로그인 아이디가 잘못 됐습니다."));
        return member.loginLogic(password);
    }

    //회원 가입
    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }

    //회원 정보 업데이트
    @Transactional
    public Member update(Long id, String password, String name, String mail, String phoneNumber){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        member.updateMember(password, name, mail, phoneNumber);

        return member;
    }

    //중복 ID 확인
    public boolean existsMember(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    //회원 탈퇴
    @Transactional
    public void delete(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        deleteBoardWrittenByMember(member);
        memberRepository.delete(member);
    }

    private void deleteBoardWrittenByMember(Member member) {
        if (boardRepository.existsByMember(member)) {
            boardRepository.deleteByMemberId(member.getId());
            em.flush();
            em.clear();
        }
    }
}
