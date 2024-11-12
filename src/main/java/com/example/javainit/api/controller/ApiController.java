package com.example.javainit.api.controller;

import com.example.javainit.user.userDetails.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/user/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(@AuthenticationPrincipal CustomUserDetails user) {
        Map<String, Object> userInfo = new HashMap<>();

        try {
            if (user != null) {
                userInfo.put("userEmail", user.getUsername());  // 이메일
                userInfo.put("userName", user.getUserName());    // 사용자 이름
                userInfo.put("userRole", user.getUserRole());    // 사용자 역할
                return ResponseEntity.ok(userInfo); // HTTP 200 OK 응답
            } else {
                userInfo.put("error", "사용자가 로그인하지 않았습니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userInfo); // HTTP 401 Unauthorized 응답
            }
        } catch (Exception e) {
            // 예외가 발생할 경우 로그를 출력하고 500 오류 응답 반환
            e.printStackTrace();
            userInfo.put("error", "서버에서 사용자 정보를 가져오는 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userInfo); // HTTP 500 응답
        }
    }
}