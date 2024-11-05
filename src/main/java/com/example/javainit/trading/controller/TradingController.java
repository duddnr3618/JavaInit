package com.example.javainit.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trading")
public class TradingController {

    @GetMapping("simulation")
    public String simulation() {
        return "trading/simulation";
    }



}
