package com.example.javainit.trading.repository;

import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.entity.TradingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradingDataRepository extends JpaRepository<TradingData, Long> {
    List<TradingData> findByUserEmailAndTradingState(String userEmail, TradingState tradingState);
}
