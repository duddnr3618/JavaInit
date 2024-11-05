package com.example.javainit.trading.service;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.entity.TradingState;
import com.example.javainit.trading.repository.TradingDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TradingService {

    private final TradingDataRepository tradingDataRepository;

    @Transactional
    public void saveOrUpdate(TradingDataDto tradingDataDto) {

        // userEmail이 "admin"이고 TradingState가 RUN인 데이터 검색
        TradingData existingData = tradingDataRepository
                .findByUserEmailAndTradingState(tradingDataDto.getUserEmail(), TradingState.RUN)
                .stream().findFirst().orElse(null);
        if (existingData != null) {
            // 기존 데이터가 있으면 해당 데이터를 업데이트
            existingData.setSeed(tradingDataDto.getSeed());
            existingData.setLossRatio(tradingDataDto.getLossRatio());
            existingData.setProfitToLossRatio(tradingDataDto.getProfitToLossRatio());
            existingData.setStopLossRatio(tradingDataDto.getStopLossRatio());
            existingData.setMargin(tradingDataDto.getMargin());
            existingData.setActualLoss(tradingDataDto.getActualLoss());
            existingData.setActualProfit(tradingDataDto.getActualProfit());
            existingData.setAdjustedProfit(tradingDataDto.getAdjustedProfit());
            existingData.setExchange(tradingDataDto.getExchange());
            existingData.setWinRate(tradingDataDto.getWinRate());
            existingData.setDateTime(tradingDataDto.getDateTime());

            // TradingState가 있으면 업데이트
            if (tradingDataDto.getTradingState() != null) {
                existingData.setTradingState(TradingState.valueOf(tradingDataDto.getTradingState()));
            }

            tradingDataRepository.save(existingData); // 업데이트된 데이터 저장
        }  else {
            // 데이터가 없으면 새로 저장
            TradingData newData = TradingData.toTradingData(tradingDataDto);
            newData.setUserEmail(tradingDataDto.getUserEmail()); // 클라이언트에서 받은 userEmail 사용
            newData.setTradingState(TradingState.RUN); // 기본 상태값 설정
            tradingDataRepository.save(newData);
        }
    }
}
