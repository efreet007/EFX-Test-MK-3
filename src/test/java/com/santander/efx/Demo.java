package com.santander.efx;

import com.santander.efx.app.MarketPriceHandler;
import com.santander.efx.app.MarketPriceRepository;
import com.santander.efx.app.MarketPriceService;
import com.santander.efx.controller.MarketPriceController;
import com.santander.efx.domain.MarketPrice;
import com.santander.efx.domain.MarketPriceCommissionAdjuster;
import com.santander.efx.messaging.CsvToMarketPriceMapper;
import com.santander.efx.messaging.MarketPriceMessagingAdapter;
import com.santander.efx.messaging.MessagingSystemHandler;
import com.santander.efx.repository.InMemoryMarketPriceRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class Demo {


    @Test
    void demo() throws IOException {
        //given
        MarketPriceRepository marketPriceRepository = new InMemoryMarketPriceRepository();
        MarketPriceService marketPriceService = new MarketPriceService(marketPriceRepository);
        MarketPriceController marketPriceController = new MarketPriceController(marketPriceService);
        MessagingSystemHandler messagingSystemHandler = new MarketPriceMessagingAdapter(
                new CsvToMarketPriceMapper(),
                new MarketPriceHandler(new MarketPriceCommissionAdjuster(), marketPriceService));


        //when
        messagingSystemHandler.onMessage(Files.readString(Path.of("src/test/resources/market-price-feed-1.csv")));
        messagingSystemHandler.onMessage(Files.readString(Path.of("src/test/resources/market-price-feed-2.csv")));

        //then
        MarketPrice priceByInstrumentName = marketPriceController.getPriceByInstrumentName("GBP/USD");

        assertThat(priceByInstrumentName).usingRecursiveComparison()
                .isEqualTo(MarketPrice.builder()
                        .id(109)
                        .ask(new BigDecimal("1.38171"))
                        .bid(new BigDecimal("1.12491"))
                        .instrumentName("GBP/USD")
                        .timestamp(Instant.parse("2020-06-01T12:01:02.100Z"))
                        .build());
    }
}
