package com.example.javainit.trading.controller;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.service.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trading")
public class TradingController {

    private final TradingService tradingService;

    @GetMapping("simulation")
    public String simulation() {
        return "trading/simulation";
    }

    @PostMapping("/submit")
    public String simulationSubmit(@ModelAttribute TradingDataDto tradingDataDto) {
        tradingService.save(tradingDataDto);
        System.out.println(">>>>" + tradingDataDto);
        return "redirect:/trading/simulation";
    }


}
