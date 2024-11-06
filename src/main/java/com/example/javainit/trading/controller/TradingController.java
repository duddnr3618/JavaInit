package com.example.javainit.trading.controller;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.service.TradingService;
import lombok.RequiredArgsConstructor;
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
    public String simulation(Model model) {

        // 기존 데이터 존재시
        String userEmail = "admin";
        TradingDataDto getTradingDataDto = tradingService.getTradingData(userEmail);
        model.addAttribute("getTradingDataDto", getTradingDataDto);
        System.out.println(">>>>> getTradingData" + getTradingDataDto);

        return "trading/simulation";
    }

    @PostMapping("/submit")
    public String simulationSubmit(@ModelAttribute TradingDataDto tradingDataDto) {
        tradingService.saveOrUpdate(tradingDataDto);
        System.out.println(">>>>post" + tradingDataDto);
        return "redirect:/trading/simulation";
    }


}
