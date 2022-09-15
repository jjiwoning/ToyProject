package jiwon.board.dto;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardWriteDto {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수입니다.")
    private String contents;

    public Board toEntity(){
        Board board = new Board(title, contents);
        return board;
    }

}
