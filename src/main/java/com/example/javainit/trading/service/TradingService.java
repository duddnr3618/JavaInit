package com.example.javainit.trading.service;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.entity.TradingState;
import com.example.javainit.trading.repository.TradingDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TradingService {

    private final TradingDataRepository tradingDataRepository;

    @Transactional
    public TradingDataDto getTradingData(String userEmail) {
        TradingData existingData = tradingDataRepository
                .findByUserEmailAndTradingState(userEmail, TradingState.RUN)
                .stream().findFirst().orElse(null);

        return existingData != null ? existingData.toTradingDataDto(existingData) : null;
    }


    @Transactional
    public void saveOrUpdate(TradingDataDto tradingDataDto) {

        // userEmail이 "admin"이고 TradingState가 RUN인 데이터 검색
        TradingData existingData = tradingDataRepository
                .findByUserEmailAndTradingState(tradingDataDto.getUserEmail(), TradingState.RUN)
                .stream().findFirst().orElse(null);
        if (existingData != null) {
            if(tradingDataDto.getTradingState().equals("WIN")){
                existingData.setLossAmount(0.0);
                existingData.setProfitAmount(tradingDataDto.getProfitAmount());
            }else if(tradingDataDto.getTradingState().equals("LOSS")){
                existingData.setLossAmount(tradingDataDto.getLossAmount());
                existingData.setProfitAmount(0.0);
            }
            // TradingState가 있으면 업데이트
            if (tradingDataDto.getTradingState() != null) {
                existingData.setTradingState(TradingState.valueOf(tradingDataDto.getTradingState()));
            }
            tradingDataRepository.save(existingData);
        }  else {
            // 데이터가 없으면 새로 저장
            TradingData newData = TradingData.toTradingData(tradingDataDto);
            newData.setUserEmail(tradingDataDto.getUserEmail());
            newData.setTradingState(TradingState.RUN); // 기본 상태값 설정
            tradingDataRepository.save(newData);
        }

    }



}
