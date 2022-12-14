package jiwon.board.repository;

import jiwon.board.domain.Board;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {
    List<Board> search(BoardSearchCondition condition, long page);

    Long getBoardTotalCount(BoardSearchCondition condition);

    Optional<BoardReadDto> findDtoById(Long id);

    void deleteByMemberId(Long memberId);
}
