package jiwon.board.controller;

import jiwon.board.domain.Member;
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

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

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

    @ExceptionHandler({IllegalArgumentException.class})
    public String loginExceptionHandler(IllegalArgumentException e, Model model){
        log.info("call Exception Handler");
        model.addAttribute("exception", e.getMessage());
        return "redirect:/login";
    }
}
