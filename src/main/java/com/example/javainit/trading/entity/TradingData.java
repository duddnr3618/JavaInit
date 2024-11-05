package com.example.javainit.trading.entity;

import com.example.javainit.trading.dto.TradingDataDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TradingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Seed (시드)
    @Column(nullable = false)
    private Double seed;  // 시드 금액

    // Loss Ratio (손실율)
    private Double lossRatio;

    // Profit to Loss Ratio (손익비)
    private Double profitToLossRatio;

    // Stop Loss Ratio (손절선)
    private Double stopLossRatio;

    // Margin (증거금)
    private Double margin;

    // Actual Loss (실제 손실)
    private Double actualLoss;

    // Actual Profit (실제 이익)
    private Double actualProfit;

    // Adjusted Profit (보정 이익)
    private Double adjustedProfit;

    // Exchange (거래소)
    @Column(length = 50)
    private String exchange;

    // Result (결과: 승/패/교전)
    @Column(length = 10)
    private String result;

    // Win Rate (승률)
    private Double winRate;

    private LocalDateTime dateTime;

    // DTO -> Entity 변환 메소드
    public static TradingData toTradingData(TradingDataDto dto) {
        TradingData tradingData = new TradingData();
        tradingData.setSeed(dto.getSeed());
        tradingData.setLossRatio(dto.getLossRatio());
        tradingData.setProfitToLossRatio(dto.getProfitToLossRatio());
        tradingData.setStopLossRatio(dto.getStopLossRatio());
        tradingData.setMargin(dto.getMargin());
        tradingData.setActualLoss(dto.getActualLoss());
        tradingData.setActualProfit(dto.getActualProfit());
        tradingData.setAdjustedProfit(dto.getAdjustedProfit());
        tradingData.setExchange(dto.getExchange());
        tradingData.setResult(dto.getResult());
        tradingData.setWinRate(dto.getWinRate());
        tradingData.setDateTime(LocalDateTime.now()); // 현재 시간 설정
        return tradingData;
    }

    // Entity -> DTO 변환 메소드
    public TradingDataDto toTradingDataDto() {
        TradingDataDto dto = new TradingDataDto();
        dto.setSeed(this.seed);
        dto.setLossRatio(this.lossRatio);
        dto.setProfitToLossRatio(this.profitToLossRatio);
        dto.setStopLossRatio(this.stopLossRatio);
        dto.setMargin(this.margin);
        dto.setActualLoss(this.actualLoss);
        dto.setActualProfit(this.actualProfit);
        dto.setAdjustedProfit(this.adjustedProfit);
        dto.setExchange(this.exchange);
        dto.setResult(this.result);
        dto.setWinRate(this.winRate);
        return dto;
    }

}
