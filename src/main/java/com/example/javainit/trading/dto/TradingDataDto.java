package com.example.javainit.trading.dto;

import lombok.Data;

@Data
public class TradingDataDto {
    private Double seed;               // 시드 금액
    private Double lossRatio;          // 손실율
    private Double profitToLossRatio;  // 손익비
    private Double stopLossRatio;      // 손절선
    private Double margin;             // 증거금
    private Double actualLoss;         // 실제 손실
    private Double actualProfit;       // 실제 이익
    private Double adjustedProfit;     // 보정 이익
    private String exchange;           // 거래소 이름
    private String result;             // 결과 (승/패/교전)
    private Double winRate;            // 승률
    private String dateTime;
}
