package com.example.javainit.trading.service;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.repository.TradingDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TradingService {

    private final TradingDataRepository tradingDataRepository;

    public void save(TradingDataDto tradingDataDto) {
        TradingData tradingData = TradingData.toTradingData(tradingDataDto);
        tradingDataRepository.save(tradingData);
    }

}
