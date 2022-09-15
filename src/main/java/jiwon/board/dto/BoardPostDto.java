package jiwon.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import jiwon.board.domain.Board;
import lombok.Data;

@Data
public class BoardPostDto {

    private Long id;

    private String title;

    private String contents;

    private String loginId;

    @QueryProjection
    public BoardPostDto(Long id, String title, String contents, String loginId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.loginId = loginId;
    }
}
