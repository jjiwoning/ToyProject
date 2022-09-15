package jiwon.board.service;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardPostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

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

        Assertions.assertThat(board.getMember()).isEqualTo(member);
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

        BoardPostDto findDto = boardService.findOne(board.getId());

        Assertions.assertThat(findDto.getTitle()).isEqualTo(board.getTitle());
    }

}