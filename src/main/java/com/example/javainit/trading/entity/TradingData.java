package com.example.javainit.trading.entity;

import com.example.javainit.trading.dto.TradingDataDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // Win Rate (승률)
    private Double winRate;

    @Column(name = "user_email")
    private String userEmail;

    // DateTime을 String 형태로 저장
    @Column(name = "date_time")
    private String dateTime;

    // 매매 끝,종료
    @Enumerated(EnumType.STRING)
    private TradingState tradingState;  // WIN, LOSS, DRAW, RUN 값 중 하나 저장

    // Entity 저장 전에 dateTime 필드 형식 지정
    @PrePersist
    @PreUpdate
    public void formatDateTime() {
        if (dateTime == null) {
            dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

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
        tradingData.setUserEmail("admin");
        tradingData.setWinRate(dto.getWinRate());

        // DTO의 TradingState 값이 없을 때 기본값 RUN으로 설정
        if (dto.getTradingState() != null) {
            tradingData.setTradingState(com.example.javainit.trading.entity.TradingState.valueOf(dto.getTradingState()));
        } else {
            tradingData.setTradingState(com.example.javainit.trading.entity.TradingState.RUN); // 기본값 설정
        }

        return tradingData;
    }


    // Entity -> DTO 변환 메소드
    public TradingDataDto toTradingDataDto() {
        TradingDataDto tradingDataDto = new TradingDataDto();
        tradingDataDto.setSeed(this.seed);
        tradingDataDto.setLossRatio(this.lossRatio);
        tradingDataDto.setProfitToLossRatio(this.profitToLossRatio);
        tradingDataDto.setStopLossRatio(this.stopLossRatio);
        tradingDataDto.setMargin(this.margin);
        tradingDataDto.setActualLoss(this.actualLoss);
        tradingDataDto.setActualProfit(this.actualProfit);
        tradingDataDto.setAdjustedProfit(this.adjustedProfit);
        tradingDataDto.setExchange(this.exchange);
        tradingDataDto.setDateTime(this.dateTime); // 이미 포맷된 상태로 DTO에 설정
        // Enum을 문자열로 변환하여 DTO에 설정
        tradingDataDto.setTradingState(this.tradingState != null ? this.tradingState.name() : null);



        tradingDataDto.setWinRate(this.winRate);

        return tradingDataDto;
    }

}