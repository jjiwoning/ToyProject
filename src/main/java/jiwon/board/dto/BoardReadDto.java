package jiwon.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class BoardReadDto {

    private Long id;

    private String title;

    private String contents;

    private String loginId;

    @QueryProjection
    public BoardReadDto(Long id, String title, String contents, String loginId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.loginId = loginId;
    }
}
