package jiwon.board.service;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.repository.BoardRepository;
import jiwon.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void write(Board board, Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 id 입니다."));
        board.updateMember(member);
        boardRepository.save(board);
    }

}
