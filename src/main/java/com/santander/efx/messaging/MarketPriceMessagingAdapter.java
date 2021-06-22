package com.santander.efx.messaging;

import com.santander.efx.app.MarketPriceHandler;

import java.util.Arrays;

public class MarketPriceMessagingAdapter implements MessagingSystemHandler {

    private final CsvToMarketPriceMapper csvToMarketPriceMapper;
    private final MarketPriceHandler marketPriceHandler;

    public MarketPriceMessagingAdapter(CsvToMarketPriceMapper csvToMarketPriceMapper, MarketPriceHandler marketPriceHandler) {
        this.csvToMarketPriceMapper = csvToMarketPriceMapper;
        this.marketPriceHandler = marketPriceHandler;
    }

    @Override
    public void onMessage(String message){
        Arrays.stream(message.split("\n")).map(csvToMarketPriceMapper::map).forEach(marketPriceHandler::handle);
    }
}
