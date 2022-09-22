package jiwon.board;

import jiwon.board.interceptor.LoginCheckInterceptor;
import jiwon.board.interceptor.SessionGetMemberInfoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/members/login", "/members/logout", "/css/**", "/*.ico", "/error");

        registry.addInterceptor(sessionGetMemberInfoInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }

    @Bean
    public SessionGetMemberInfoInterceptor sessionGetMemberInfoInterceptor() {
        return new SessionGetMemberInfoInterceptor();
    }

}
