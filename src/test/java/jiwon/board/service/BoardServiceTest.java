package jiwon.board.service;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    EntityManager em;
    @Autowired BoardService boardService;
    @Autowired MemberService memberService;

    @Test
    @DisplayName("게시물 저장 테스트")
    void save(){
        Board board = new Board("aaa", "aaa");
        Member member = new Member("test", "test!", "test", "aaa@aaa.com", "0101010");

        memberService.join(member);
        boardService.write(board, member.getId());

        assertThat(board.getMember()).isEqualTo(member);
    }

    @Test
    @DisplayName("게시물 단건조회 테스트")
    void findOne(){
        Board board = new Board("aaa", "aaa");
        Member member = new Member("test", "test!", "test", "aaa@aaa.com", "0101010");

        memberService.join(member);
        boardService.write(board, member.getId());

        em.flush();
        em.clear();

        BoardReadDto findDto = boardService.findOne(board.getId());

        assertThat(findDto.getTitle()).isEqualTo(board.getTitle());
    }

    @Test
    @DisplayName("게시물 여러 건 조회 테스트")
    void findAll(){
        //given
        Member memberA = new Member("memberA", "aaa", "aaa", "aaa@aaa.com", "12334");
        Member memberB = new Member("memberB", "aaa", "bbb", "aaa@aaa.com", "12334");

        em.persist(memberA);
        em.persist(memberB);

        for (int i = 0; i < 100; i++) {
            Member selectedMember = i % 2 == 0 ? memberA : memberB;
            Board board = new Board("test" + i, "contents" + i, selectedMember);
            em.persist(board);
        }

        //when
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setTitleAndContent("contents2");
        long page = 0;

        List<Board> result = boardService.findList(condition, page);

        //then
        assertThat(result.size()).isEqualTo(11);
        assertThat(result).extracting("contents").contains("contents2", "contents20", "contents21");
        assertThat(result.get(0).getTitle()).isEqualTo("test29");
    }

}