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
                        .requestMatchers("/user/loginPage", "/user/joinPage", "/css/**", "/js/**", "/images/**").permitAll() // 로그인 및 회원가입, 정적 리소스 허용
                        .anyRequest().authenticated() // 그 외 모든 요청 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/user/loginPage") // 사용자 정의 로그인 페이지 경로
                        .loginProcessingUrl("/user/login") // 로그인 처리 URL
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 루트 경로로 이동
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/user/loginPage") // 로그아웃 후 로그인 페이지로 이동
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