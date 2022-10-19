package jiwon.board.dto;

import jiwon.board.domain.Board;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardWriteDto {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String contents;

    public Board toEntity(){
        Board board = new Board(title, contents);
        return board;
    }

    public void toDto(Board board) {
        this.title = board.getTitle();
        this.contents = board.getContents();
    }

}
