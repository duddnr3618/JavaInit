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
    private Double seed;  // 시드 금액

    private Double afterSeed;

    // Loss Ratio (손실율)
    private Double lossRatio;

    // Profit to Loss Ratio (손익비)
    private Double profitToLossRatio;

    // Stop Loss Line (손절선)
    private Double lossLine;

    // Margin (증거금)
    private Double margin;

    // Actual Loss (실제 손실)
    private Double lossAmount;

    // Actual Profit (실제 이익)
    private Double profitAmount;

    // Exchange (거래소)
    @Column(length = 50)
    private String exchange;

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
        tradingData.setAfterSeed(dto.getAfterSeed());
        tradingData.setLossRatio(dto.getLossRatio());
        tradingData.setProfitToLossRatio(dto.getProfitToLossRatio());
        tradingData.setLossLine(dto.getLossLine());
        tradingData.setMargin(dto.getMargin());
        tradingData.setLossAmount(dto.getLossAmount());
        tradingData.setProfitAmount(dto.getProfitAmount());
        tradingData.setExchange(dto.getExchange());
        tradingData.setUserEmail(dto.getUserEmail());

        // DTO의 TradingState 값이 없을 때 기본값 RUN으로 설정
        if (dto.getTradingState() != null) {
            tradingData.setTradingState(com.example.javainit.trading.entity.TradingState.valueOf(dto.getTradingState()));
        } else {
            tradingData.setTradingState(com.example.javainit.trading.entity.TradingState.RUN); // 기본값 설정
        }

        return tradingData;
    }


    // Entity -> DTO 변환 메소드
    public static TradingDataDto toTradingDataDto(TradingData tradingData) {
        TradingDataDto tradingDataDto = new TradingDataDto();
        tradingDataDto.setUserEmail(tradingData.userEmail);
        tradingDataDto.setSeed(tradingData.seed);
        tradingDataDto.setAfterSeed(tradingData.afterSeed);
        tradingDataDto.setLossRatio(tradingData.lossRatio);
        tradingDataDto.setProfitToLossRatio(tradingData.profitToLossRatio);
        tradingDataDto.setLossLine(tradingData.lossLine);
        tradingDataDto.setMargin(tradingData.margin);
        tradingDataDto.setLossAmount(tradingData.lossAmount);
        tradingDataDto.setProfitAmount(tradingData.profitAmount);
        tradingDataDto.setExchange(tradingData.exchange);
        tradingDataDto.setDateTime(tradingData.dateTime); // 이미 포맷된 상태로 DTO에 설정
        // Enum을 문자열로 변환하여 DTO에 설정
        tradingDataDto.setTradingState(tradingData.tradingState != null ? tradingData.tradingState.name() : null);

        return tradingDataDto;
    }

}
