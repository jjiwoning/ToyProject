package jiwon.board.dto;

import jiwon.board.domain.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberSaveDto {

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수 입니다.")
    private String name;

    @Email(message = "xxx@xxx.xxx의 이메일 형태로 작성해주세요.")
    @NotNull(message = "이메일은 필수 입니다.") // @Email 어노테이션이 null 값을 허용하기 때문에 넣어줘야함
    private String mail;

    @NotEmpty(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    //Dto -> Entity 변환 메서드
    public Member toEntity(){
        Member member = new Member(loginId, password, name, mail, phoneNumber);
        return member;
    }
}

