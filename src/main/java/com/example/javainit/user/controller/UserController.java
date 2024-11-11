package com.example.javainit.user.controller;

import com.example.javainit.user.dto.UserDto;
import com.example.javainit.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/joinPage")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto userDto) {

        userService.saveOrUpdate(userDto);
        return "redirect:/user/loginPage";
    }




}
