package jiwon.board.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String eMail;

    private String phoneNumber;

    public Member(String loginId, String password, String name, String eMail, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }
}
