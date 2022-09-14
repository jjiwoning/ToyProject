package jiwon.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jiwon.board.dto.BoardPostDto;
import jiwon.board.dto.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardPostDto> search(BoardSearchCondition condition, Pageable pageable) {
        return null;
    }
}
