package com.example.javainit.trading.controller;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.service.TradingService;
import com.example.javainit.user.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        // RUN 상태만 추가
        if (getTradingDataDto != null && "RUN".equals(getTradingDataDto.getTradingState())) {
            model.addAttribute("getTradingDataDto", getTradingDataDto);
        }

        return "trading/simulation";
    }

    @PostMapping("/submit")
    public String simulationSubmit(@ModelAttribute TradingDataDto tradingDataDto) {
        tradingService.saveOrUpdate(tradingDataDto);
        return "redirect:/trading/simulation";
    }

    @GetMapping("/historyPage")
    public String historyPage(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        if(user == null){
            return "redirect:/user/loginPage";
        }
        String userEmail = user.getUsername();
        List<TradingDataDto> tradingDataDtoList = tradingService.getHistoricalData(userEmail);
        TradingDataDto lastTradingDataDto = null;
        if (!tradingDataDtoList.isEmpty()) {
            lastTradingDataDto = tradingDataDtoList.get(tradingDataDtoList.size() - 1);
        }

        model.addAttribute("data",lastTradingDataDto);
        model.addAttribute("getHistoryDataDto", tradingDataDtoList);
        model.addAttribute("results",tradingService.countStates(userEmail));
        return "trading/history";
    }


}
