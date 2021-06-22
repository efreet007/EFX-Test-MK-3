package com.santander.efx.repository;

import com.santander.efx.app.MarketPriceRepository;
import com.santander.efx.domain.MarketPrice;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMarketPriceRepository implements MarketPriceRepository {

    private final Map<String, MarketPrice> marketPrices = new HashMap<>();

    @Override
    public MarketPrice getPriceByInstrumentName(String instrumentName) {
        return marketPrices.get(instrumentName);
    }

    @Override
    public void add(MarketPrice marketPrice) {
        marketPrices.put(marketPrice.getInstrumentName(), marketPrice);
    }
}
