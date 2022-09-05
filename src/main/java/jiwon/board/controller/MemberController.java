package jiwon.board.controller;

import jiwon.board.domain.Member;
import jiwon.board.dto.MemberSaveDto;
import jiwon.board.exception.LoginFailException;
import jiwon.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //로그인
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult){
        Member member = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
//        try{
//            Member member = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
//        }catch (IllegalArgumentException e){
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가");
//        }
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

    @ExceptionHandler({LoginFailException.class})
    public String loginExceptionHandler(LoginFailException e, Model model){
        log.info("call Exception Handler");
        model.addAttribute("exception", e.getMessage());
        return "redirect:/login";
    }
}
