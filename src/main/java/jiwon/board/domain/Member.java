package jiwon.board.domain;

import jiwon.board.exception.LoginFailException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String mail;

    private String phoneNumber;

    public Member(String loginId, String password, String name, String mail, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    //로그인 처리 메서드
    public Member loginLogic(String password){
        if(this.password.equals(password)){
            return this;
        }
        throw new LoginFailException("잘못된 비밀번호를 입력했습니다.");
    }

    //회원 수정 메서드
    public void updateMember(String password, String name, String mail, String phoneNumber){
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }
}
