package jiwon.board.service;

import jiwon.board.domain.Member;
import jiwon.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 정보 조회
    public Member findMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        return member;
    }

    //로그인
    public Member login(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("로그인 아이디가 잘못 됐습니다."));
        return member.loginLogic(password);
    }

    //회원 가입
    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }

    //회원 정보 업데이트
    @Transactional
    public void update(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        //TODO: 어떤 방식으로 업데이트 파라미터 넘길지 고민
    }

    //회원 탈퇴
    @Transactional
    public void delete(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        memberRepository.delete(member);
    }
}
