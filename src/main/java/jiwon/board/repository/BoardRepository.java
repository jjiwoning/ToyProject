package jiwon.board.repository;

import jiwon.board.domain.Board;

import java.util.List;

public interface BoardRepository {

    Long save(Board board);

    void delete();

    List<Board> findAll();
}
