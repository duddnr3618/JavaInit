package com.example.javainit.trading.repository;

import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.entity.TradingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradingDataRepository extends JpaRepository<TradingData, Long> {
    List<TradingData> findByUserEmailAndTradingStateIn(String userEmail, List<TradingState> tradingStates);

    Long countByUserEmailAndTradingState(String userEmail, TradingState tradingState);

    // userEmail과 특정 TradingState를 조건으로 데이터 조회
    Optional<TradingData> findByUserEmailAndTradingState(String userEmail, TradingState tradingState);

    List<TradingData> findAllByUserEmail(String userEmail);
}
