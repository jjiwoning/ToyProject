package jiwon.board.interceptor;

import jiwon.board.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("call interceptor");
        String requestURI = request.getRequestURI();

        //세션 가져오기
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            //이 경우 세션 x -> 로그인 화면으로 보내야 된다.
            response.sendRedirect("/members/login?redirectURL=" + requestURI);
            return false; // 여기서 멈춘다.
        }

        return true;
    }
}
