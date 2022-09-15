package jiwon.board.service;

import jiwon.board.domain.Member;
import jiwon.board.exception.LoginFailException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @DisplayName("회원 탈퇴 테스트")
    void delete(){
        Member member = new Member("aaa", "1234", "tamtam", "asdf@asd.com", "010101010");
        memberService.join(member);

        memberService.delete(member.getId());

        Assertions.assertThat(memberService.existsMember(member.getLoginId())).isFalse();
    }

    @Test
    @DisplayName("회원가입, 로그인 테스트")
    void login(){
        Member member = new Member("aaa", "1234", "tamtam", "asdf@asd.com", "010101010");
        memberService.join(member);

        //로그인 성공
        Member loginMember = memberService.login(member.getLoginId(), member.getPassword());
        Assertions.assertThat(loginMember).isEqualTo(member);

        //로그인 실패 (아이디 오류)
        Assertions.assertThatThrownBy(() -> memberService.login("aaa2", "1234"))
                .isInstanceOf(LoginFailException.class)
                .hasMessageContaining("로그인 아이디가 잘못 됐습니다.");

        //로그인 실패 (비밀번호 오류)
        Assertions.assertThatThrownBy(() -> memberService.login("aaa", "1235"))
                .isInstanceOf(LoginFailException.class)
                .hasMessageContaining("잘못된 비밀번호를 입력했습니다.");
    }
}