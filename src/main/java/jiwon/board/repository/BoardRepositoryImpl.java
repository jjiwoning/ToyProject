package jiwon.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jiwon.board.domain.Board;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;
import jiwon.board.dto.QBoardReadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static jiwon.board.domain.QBoard.*;
import static jiwon.board.domain.QMember.member;

@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> search(BoardSearchCondition condition, long page) {
        log.info("call boardRepository.search()");

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(condition.getTitleAndContent())) {
            builder.or(board.title.contains(condition.getTitleAndContent()));
            builder.or(board.contents.contains(condition.getTitleAndContent()));
        }

        return queryFactory
                .select(board)
                .from(board)
                .join(board.member, member).fetchJoin()
                .where(builder)
                .offset((Math.max(1, page) -1) * 20)
                .limit(20)
                .orderBy(board.createdDate.desc())
                .fetch();
    }

    @Override
    public Optional<BoardReadDto> findDtoById(Long id) {
        log.info("call findDtoById");
        return Optional.ofNullable(queryFactory
                .select(new QBoardReadDto(
                        board.id,
                        board.title,
                        board.contents,
                        member.id,
                        member.loginId
                )).from(board)
                .join(board.member, member)
                .where(board.id.eq(id))
                .fetchOne());
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        queryFactory
                .delete(board)
                .where(board.member.id.in(memberId))
                .execute();
    }

}
