package jiwon.board.interceptor;

import jiwon.board.domain.Member;
import jiwon.board.service.MemberService;
import jiwon.board.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SessionGetMemberInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("call sessionInterceptor");
        HttpSession session = request.getSession(false);
        if(session != null){
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Member member = memberService.findMember(memberId);
            ModelMap modelMap = modelAndView.getModelMap();
            modelMap.addAttribute("sessionMember", member.getLoginId());
        }
    }
}
