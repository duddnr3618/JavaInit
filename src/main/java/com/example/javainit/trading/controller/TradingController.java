package com.example.javainit.trading.controller;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.service.TradingService;
import com.example.javainit.user.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trading")
public class TradingController {

    private final TradingService tradingService;

    @GetMapping("/simulation")
    public String simulation(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        if(user == null){

            return "redirect:/";
        }
        
        // 로그인 시
        String userEmail = user.getUsername();
        TradingDataDto getTradingDataDto = tradingService.getTradingData(userEmail);
        model.addAttribute("getTradingDataDto", getTradingDataDto);

        return "trading/simulation";
    }

    @PostMapping("/submit")
    public String simulationSubmit(@ModelAttribute TradingDataDto tradingDataDto) {
        tradingService.saveOrUpdate(tradingDataDto);
        return "redirect:/trading/simulation";
    }


}
