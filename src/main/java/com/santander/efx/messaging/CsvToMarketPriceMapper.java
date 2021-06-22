package com.santander.efx.messaging;

import com.santander.efx.domain.MarketPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CsvToMarketPriceMapper {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    public MarketPrice map(String csvPrice) {
        String[] csvValues = csvPrice.trim().split("\\s*,\\s*");
        return MarketPrice.builder()
                .id(Integer.parseInt(csvValues[0]))
                .instrumentName(csvValues[1])
                .bid(new BigDecimal(csvValues[2]))
                .ask(new BigDecimal(csvValues[3]))
                .timestamp(LocalDateTime.parse(csvValues[4], dateTimeFormatter).atZone(ZoneId.of("UTC")).toInstant())
                .build();
    }
}
