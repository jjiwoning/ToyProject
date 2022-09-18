package jiwon.board.service;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;
import jiwon.board.repository.BoardRepository;
import jiwon.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public BoardReadDto findOne(Long id){
        return boardRepository.findDtoById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public List<Board> findList(BoardSearchCondition condition, Long page) {
        return boardRepository.search(condition, page);
    }

    @Transactional
    public void update(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        boardRepository.delete(board);
    }
}
