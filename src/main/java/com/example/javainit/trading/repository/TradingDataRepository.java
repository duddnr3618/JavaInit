package com.example.javainit.trading.repository;

import com.example.javainit.trading.entity.TradingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingDataRepository extends JpaRepository<TradingData, Long> {
}
