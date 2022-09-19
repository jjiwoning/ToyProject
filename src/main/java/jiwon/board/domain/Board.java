package jiwon.board.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public Board(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    //연관관계 메서드
    public void updateMember(Member member){
        this.member = member;
    }

    //Board 작성 Member 체크 메서드
    public boolean isWritten(Long memberId) {
        if (this.member.getId().equals(memberId)) {
            return true;
        }
        return false;
    }

    //update 메서드
    public void updateBoard(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
