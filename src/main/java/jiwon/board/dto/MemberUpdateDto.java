package jiwon.board.dto;

import jiwon.board.domain.Member;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MemberUpdateDto {

    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용해주세요.")
    private String password;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Size(min = 2, max = 8, message = "이름은 2~8 글자 사이로 입력해주세요")
    private String name;

    @NotEmpty(message = "이메일은 필수 입니다.")
    @Email(message = "xxx@xxx.xxx의 이메일 형태로 작성해주세요.")
    private String mail;

    @NotEmpty(message = "전화번호는 필수입니다.")
    @Positive(message = "전화번호는 숫자만 입력해주세요 (010xxxxxxxx 형태)")
    @Size(min = 11, max = 11, message = "전화번호 형식이 올바르지 않습니다.(010xxxxxxxx 형태)")
    private String phoneNumber;

    public void toDto(Member member){
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.mail = member.getMail();
        this.phoneNumber = member.getPhoneNumber();
    }
}
