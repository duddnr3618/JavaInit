package com.example.javainit.main.controller;

import com.example.javainit.user.userDetails.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        if(user != null) {
            model.addAttribute("userEmail", user.getUsername());  // 이메일
            model.addAttribute("userName", user.getUserName());    // 사용자 이름
            model.addAttribute("userRole", user.getUserRole());    // 사용자 역할
        }
        return "main/index";
    }


}
