package com.example.javainit.config;

import com.example.javainit.user.userDetails.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * 특정 경로에 대한 액세스 설정
 * requestMatchers() : 특정 요청과 일치하는 url에 대한 액세스 설정
 * permitAll() : 누구나 접근 가능하게 설정, 즉 "/login", "/signup", "/user"로 요청이 오면 인증,인가 없이 접근 가능
 * anyRequest() : 위에서 설정한 url 이외의 요청
 * authenticated() : 별도의 인가는 필요하지 않지만 인증이 성공된 상태에서 접근 가능
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 개발 중 CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/loginPage", "/user/joinPage", "/**").permitAll() // 모든 경로 허용
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/loginPage")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("userEmail") // 사용자 정의 필드명 사용
                        .defaultSuccessUrl("/", true)
                        .failureHandler((request, response, exception) -> {
                            String errorMessage = "자격 증명에 실패하였습니다.";
                            String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString());
                            response.sendRedirect("/user/loginPage?error=true&message=" + encodedMessage);
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/loginPage") // 로그아웃 후 로그인 페이지로 이동
                        .invalidateHttpSession(true)    // 세션무효화
                        .deleteCookies("JSESSIONID")    // 쿠키삭제
                        .permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}