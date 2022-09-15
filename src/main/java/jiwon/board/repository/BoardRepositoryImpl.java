package jiwon.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jiwon.board.domain.QBoard;
import jiwon.board.dto.BoardPostDto;
import jiwon.board.dto.BoardSearchCondition;
import jiwon.board.dto.QBoardPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static jiwon.board.domain.QBoard.*;
import static jiwon.board.domain.QMember.member;

@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardPostDto> search(BoardSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public BoardPostDto findDtoById(Long id) {
        log.info("call findDtoById");
        return queryFactory
                .select(new QBoardPostDto(
                        board.id,
                        board.title,
                        board.contents,
                        member.loginId
                )).from(board)
                .join(board.member, member)
                .where(board.id.eq(id))
                .fetchOne();
    }
}
