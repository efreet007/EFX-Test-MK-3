package com.santander.efx.app;

import com.santander.efx.domain.MarketPrice;
import com.santander.efx.domain.MarketPriceCommissionAdjuster;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketPriceHandler {

    private final MarketPriceCommissionAdjuster marketPriceCommissionAdjuster;
    private final MarketPriceService marketPriceService;

    public void handle(MarketPrice marketPrice) {
        MarketPrice adjustedMarketPrice = marketPriceCommissionAdjuster.applyCommission(marketPrice);
        marketPriceService.add(adjustedMarketPrice);
    }
}
