package jiwon.board.repository;

import jiwon.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;

    @Test
    @DisplayName("저장 기능 테스트")
    void save(){
        //given
        Board board = new Board("hello", "hello", null);

        //when
        Board savedBoard = boardRepository.save(board);
        Board findBoard = boardRepository.findById(savedBoard.getId()).get();

        //then
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    @DisplayName("조회 기능 테스트")
    void find(){
        //given
        Board board1 = new Board("hello1", "hello", null);
        Board board2 = new Board("hello2", "hello", null);

        Board savedBoard1 = boardRepository.save(board1);
        Board savedBoard2 = boardRepository.save(board2);

        //when
        //단건 조회
        Board findBoard1 = boardRepository.findById(savedBoard1.getId()).get();

        //리스트 조회
        List<Board> findBoards = boardRepository.findAll();

        //then
        //단건 조회
        assertThat(findBoard1).isEqualTo(board1);

        //리스트 조회
        assertThat(findBoards).contains(board1, board2);
    }
}