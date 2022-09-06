package jiwon.board.controller;

import jiwon.board.domain.Member;
import jiwon.board.dto.MemberSaveDto;
import jiwon.board.exception.LoginFailException;
import jiwon.board.service.MemberService;
import jiwon.board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //로그인
    @GetMapping("/login")
    public String loginForm(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, @RequestParam(name = "exception", defaultValue = "") String message) {
        if(message.equals("로그인 아이디가 잘못 됐습니다.")){
            bindingResult.rejectValue("loginId", "아이디 오류", message);
        }
        if(message.equals("잘못된 비밀번호를 입력했습니다.")){
            bindingResult.rejectValue("password", "비밀번호 오류", message);
        }
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request){
        Member member = memberService.login(loginForm.getLoginId(), loginForm.getPassword());

        HttpSession httpSession = request.getSession(); // 세션이 있으면 기존 세션 리턴, 없으면 신규 생성
        httpSession.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "home";
    }

    //회원 가입
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") MemberSaveDto memberSaveDto){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("member") MemberSaveDto memberSaveDto, BindingResult bindingResult){
        //valid 로직에서 에러가 발견되면 -> 잘못된 값을 입력했을 경우
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }
        if(memberService.existsMember(memberSaveDto.getLoginId())){
            bindingResult.rejectValue("loginId", "duplicateLoginId", "이미 가입한 회원ID입니다.");
            return "members/addMemberForm";
        }
        Member member = memberSaveDto.toEntity();
        memberService.join(member);
        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            httpSession.invalidate();
        }
        return "redirect:/";
    }

    @ExceptionHandler({LoginFailException.class})
    public String loginExceptionHandler(LoginFailException e, Model model){
        log.info("call Exception Handler");
        model.addAttribute("exception", e.getMessage());
        return "redirect:/login";
    }
}
