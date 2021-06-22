package com.santander.efx.domain;

import java.math.BigDecimal;

public class MarketPriceCommissionAdjuster {

    public MarketPrice applyCommission(MarketPrice marketPrice) {
        return marketPrice.toBuilder()
                .ask(applyCommission(marketPrice.getAsk(), new BigDecimal("0.1")))
                .bid(applyCommission(marketPrice.getBid(), new BigDecimal("-0.1")))
                .build();
    }

    private BigDecimal applyCommission(BigDecimal price, BigDecimal commission) {
        return price.add(price.multiply(commission));
    }
}
