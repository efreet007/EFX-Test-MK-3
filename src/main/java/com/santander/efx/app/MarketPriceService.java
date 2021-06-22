package com.santander.efx.app;

import com.santander.efx.domain.MarketPrice;
import lombok.RequiredArgsConstructor;

//@Transactional - in production transactions spanning few repository calls would be handled here
@RequiredArgsConstructor
public class MarketPriceService {

    private final MarketPriceRepository marketPriceRepository;

    public MarketPrice getPriceByInstrumentName(String instrumentName){
        return marketPriceRepository.getPriceByInstrumentName(instrumentName);
    }

    public void add(MarketPrice marketPrice) {
        marketPriceRepository.add(marketPrice);
    }
}
