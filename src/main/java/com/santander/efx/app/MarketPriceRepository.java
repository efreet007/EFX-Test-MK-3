package com.santander.efx.app;

import com.santander.efx.domain.MarketPrice;

public interface MarketPriceRepository {
    MarketPrice getPriceByInstrumentName(String instrumentName);

    void add(MarketPrice marketPrice);
}
