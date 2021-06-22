package com.santander.efx.messaging;

import com.santander.efx.domain.MarketPrice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CsvToMarketPriceMapperTest {

    @Test
    void mapCsvLineToMarketPrice() {

        //given
        CsvToMarketPriceMapper csvToMarketPriceMapper = new CsvToMarketPriceMapper();

        //when
        MarketPrice marketPrice = csvToMarketPriceMapper.map("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");

        //then
        assertThat(marketPrice).usingRecursiveComparison().isEqualTo(MarketPrice.builder()
                .id(106)
                .bid(new BigDecimal("1.1000"))
                .ask(new BigDecimal("1.2000"))
                .instrumentName("EUR/USD")
                .timestamp(Instant.parse("2020-06-01T12:01:01.001Z"))
                .build());
    }
}