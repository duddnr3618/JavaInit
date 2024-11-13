package com.example.javainit.trading.service;

import com.example.javainit.trading.dto.TradingDataDto;
import com.example.javainit.trading.entity.TradingData;
import com.example.javainit.trading.entity.TradingState;
import com.example.javainit.trading.repository.TradingDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TradingService {

    private final TradingDataRepository tradingDataRepository;

    @Transactional
    public TradingDataDto getTradingData(String userEmail) {
        // 여러 상태 조회를 위해 RUN, WIN, LOSS를 포함한 상태 리스트 생성
        List<TradingState> states = Arrays.asList(TradingState.RUN, TradingState.WIN, TradingState.LOSS);

        TradingData existingData = tradingDataRepository
                .findByUserEmailAndTradingState(userEmail, TradingState.RUN).orElse(null);

        return existingData != null ? existingData.toTradingDataDto(existingData) : null;
    }

    @Transactional
    public Map<String, Object> countStates(String userEmail) {
        // WIN과 LOSS 상태 개수를 조회하여 저장
        Map<String, Object> result = new HashMap<>();
        long winCount = tradingDataRepository.countByUserEmailAndTradingState(userEmail, TradingState.WIN);
        long lossCount = tradingDataRepository.countByUserEmailAndTradingState(userEmail, TradingState.LOSS);
        long drawPlus = tradingDataRepository.countByUserEmailAndTradingState(userEmail, TradingState.DRAW_PLUS);
        long drawMinus = tradingDataRepository.countByUserEmailAndTradingState(userEmail, TradingState.DRAW_MINUS);
        long totalCount = winCount + lossCount + drawPlus + drawMinus;
        // 총 시도 횟수가 0이 아닐 때만 winRate를 계산
        double winRate = totalCount > 0 ? ((double) (winCount + drawPlus) / totalCount) * 100 : 0.0;

        result.put("winCount", winCount);
        result.put("lossCount", lossCount);
        result.put("totalCount", totalCount);
        result.put("winRate", winRate);

        return result;
    }

    @Transactional
    public void saveOrUpdate(TradingDataDto tradingDataDto) {
        List<TradingState> states = Arrays.asList(TradingState.RUN);

        TradingData existingData = tradingDataRepository
                .findByUserEmailAndTradingStateIn(tradingDataDto.getUserEmail(), states)
                .stream().findFirst().orElse(null);

        if (existingData != null) {
            if (tradingDataDto.getTradingState().equals("WIN")) {
                existingData.setLossAmount(0.0);
                existingData.setProfitAmount(tradingDataDto.getProfitAmount());
                double afterSeed = tradingDataDto.getProfitAmount() + tradingDataDto.getSeed();
                existingData.setAfterSeed(afterSeed);
            } else if (tradingDataDto.getTradingState().equals("LOSS")) {
                existingData.setLossAmount(tradingDataDto.getLossAmount());
                existingData.setProfitAmount(0.0);
                double afterSeed = tradingDataDto.getSeed() - tradingDataDto.getLossAmount();
                existingData.setAfterSeed(afterSeed);
            }else if(tradingDataDto.getTradingState().equals("DRAW_PLUS")){
                existingData.setLossAmount(0.0);
                existingData.setProfitAmount(tradingDataDto.getProfitAmount());
                double afterSeed = tradingDataDto.getSeed();
                existingData.setAfterSeed(afterSeed);
            }else if(tradingDataDto.getTradingState().equals("DRAW_MINUS")){
                existingData.setLossAmount(tradingDataDto.getLossAmount());
                existingData.setProfitAmount(0.0);
                double afterSeed = tradingDataDto.getSeed();
                existingData.setAfterSeed(afterSeed);
            }
            if (tradingDataDto.getTradingState() != null) {
                existingData.setTradingState(TradingState.valueOf(tradingDataDto.getTradingState()));
            }
            tradingDataRepository.save(existingData);
        } else {
            TradingData newData = TradingData.toTradingData(tradingDataDto);
            newData.setUserEmail(tradingDataDto.getUserEmail());
            newData.setTradingState(TradingState.RUN);
            tradingDataRepository.save(newData);
        }
    }

    public List<TradingDataDto> getHistoricalData(String userEmail) {
        List<TradingData> historicalDataList = tradingDataRepository.findAllByUserEmail(userEmail);

        // 각 TradingData 엔티티를 TradingDataDto로 변환하여 리스트로 반환
        return historicalDataList.stream()
                .map(TradingData::toTradingDataDto) // 엔티티의 인스턴스 메서드 호출
                .collect(Collectors.toList());
    }


}
