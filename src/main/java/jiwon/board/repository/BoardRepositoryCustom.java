package jiwon.board.repository;

import jiwon.board.dto.BoardPostDto;
import jiwon.board.dto.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardPostDto> search(BoardSearchCondition condition, Pageable pageable);

    BoardPostDto findDtoById(Long id);
}
