package com.santander.efx.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder(toBuilder = true)
public class MarketPrice {

    private final int id;
    private final String instrumentName;
    private final BigDecimal bid;
    private final BigDecimal ask;
    private final Instant timestamp;

}
