package jiwon.board.repository;

import jiwon.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Board> findAll() {
        return null;
    }
}
