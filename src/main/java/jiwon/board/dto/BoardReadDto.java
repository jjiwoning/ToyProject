package jiwon.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class BoardReadDto {

    private Long boardId;

    private String title;

    private String contents;

    private Long memberId;

    private String loginId;

    @QueryProjection
    public BoardReadDto(Long boardId, String title, String contents,Long memberId, String loginId) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.memberId = memberId;
        this.loginId = loginId;
    }
}
