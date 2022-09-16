package jiwon.board.repository;

import jiwon.board.domain.Board;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> search(BoardSearchCondition condition, long page);

    BoardReadDto findDtoById(Long id);
}
