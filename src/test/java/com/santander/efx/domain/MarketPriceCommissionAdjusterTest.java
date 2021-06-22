package com.santander.efx.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MarketPriceCommissionAdjusterTest {

    @Test
    void applyCommission() {
        //given
        MarketPriceCommissionAdjuster marketPriceCommissionAdjuster = new MarketPriceCommissionAdjuster();
        MarketPrice marketPrice = MarketPrice.builder()
                .bid(new BigDecimal("100"))
                .ask(new BigDecimal("200"))
                .build();

        //when
        MarketPrice priceWithCommission = marketPriceCommissionAdjuster.applyCommission(marketPrice);

        //then
        assertThat(priceWithCommission).usingRecursiveComparison().isEqualTo(MarketPrice.builder()
                .bid(new BigDecimal("90.0"))
                .ask(new BigDecimal("220.0"))
                .build()
        );
    }
}