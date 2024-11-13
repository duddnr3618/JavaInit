package com.example.javainit.trading.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TradingDataDto {
    private Double seed;               // 시드 금액
    private Double afterSeed;
    private Double lossRatio;          // 손실율
    private Double profitToLossRatio;  // 손익비
    private Double lossLine;            // 손절선
    private Double margin;             // 증거금
    private Double lossAmount;         // 실제 손실
    private Double profitAmount;       // 실제 이익
    private String exchange;           // 거래소 이름
    private String tradingState;       // 결과 (승/패/교전)
    private Double winRate;            // 승률
    private String dateTime;
    private String userEmail;
}
