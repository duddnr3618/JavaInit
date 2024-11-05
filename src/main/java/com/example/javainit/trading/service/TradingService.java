package com.example.javainit.trading.service;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.repository.TradingDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final TradingDataRepository tradingDataRepository;

    public void save(TradingDataDto tradingDataDto) {
        TradingData tradingData = TradingData.toTradingData(tradingDataDto);
        if(tradingData.getResult() == null) {
            tradingData.setResult("ING");
        }
        tradingDataRepository.save(tradingData);
    }




}
