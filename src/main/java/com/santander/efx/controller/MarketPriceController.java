package com.santander.efx.controller;

import com.santander.efx.domain.MarketPrice;
import com.santander.efx.app.MarketPriceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketPriceController {

    private final MarketPriceService marketPriceService;

    //@GetMapping("/prices/{instrumentName}") // annotations from spring or JAX-RS
    public MarketPrice getPriceByInstrumentName(/* @PathVariable */ String instrumentName) {
        return marketPriceService.getPriceByInstrumentName(instrumentName);
    }
}
