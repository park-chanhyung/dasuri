package com.project.dasuri.member.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final AuthenticationFailureHandler customAuthenticationFailureHandler;


    public SecurityConfig(AuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
        web.ignoring().requestMatchers("/css/**", "/img/**", "/js/**");
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ //에러가왜났지?
        //특정 경로 인가 작업
        http
                .authorizeHttpRequests((auth)->auth

                        .requestMatchers("/","/**").permitAll() //접근제한 경로 순서 (깔때기모양)
//                        .requestMatchers("/admin").hasRole("ADMIN")
                        //마이페이지 뒤에 여러개의 유저아이디
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")//모든아이디를 설정할수없기때문에 와일드카드를 사용
                        .anyRequest().authenticated()
                );

        //admin 접근 거부만 뜸 로그인폼설정을 안했기 때문
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .failureHandler(customAuthenticationFailureHandler)
                        .defaultSuccessUrl("/index") //login Success 매핑

                        //널포인트 오류로 강제로 파라미터명 설정. 강제로 안하면 로그인 안됨.
                        .usernameParameter("userId")
                        .passwordParameter("userPwd")
                        .permitAll() //아무나 들어올 수 있도록
                );

        //배포환경에서는 enable , defualt값이 enable / enable을 하기 위해서는 토큰관리 시스템 구축 필요 ->csrffilter 로 토큰검증
        http
//                .csrf((csrf) -> csrf
//                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")));
                .csrf((auth) -> auth.disable()); //나중에 다시 enable함

        //다중 로그인 설정
//        http
//                .sessionManagement((auth) -> auth
//                        .maximumSessions(1) //하나의 아이디에 대한 다중 로그인 허용 개수
//                        .maxSessionsPreventsLogin(true)); //다중 로그인 개수 초과했을 경우 처리방법
        //true : 초과시 새로운 로그인 차단 , false 초과시 기존 세션 하나 삭제

        //세션 고정 보호
//        http
//                .sessionManagement((auth)->auth
//                        .sessionFixation().changeSessionId()
//                );
////                - sessionManagement().sessionFixation().none() : 로그인 시 세션 정보 변경 안함
////                - sessionManagement().sessionFixation().newSession() : 로그인 시 세션 새로 생성
////                - sessionManagement().sessionFixation().changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경 * 주로 많이씀
        return http.build();
    }

}
