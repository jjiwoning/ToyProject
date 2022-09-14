package jiwon.board.service;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired MemberService memberService;

    @Test
    @DisplayName("게시물 저장 테스트")
    void save(){
        Board board = new Board("aaa", "aaa");
        Member member = new Member("test", "test!", "test", "aaa@aaa.com", "0101010");

        board.updateMember(member);

        memberService.join(member);
        boardService.write(board, member.getId());

        Assertions.assertThat(board.getMember()).isEqualTo(member);
    }

}