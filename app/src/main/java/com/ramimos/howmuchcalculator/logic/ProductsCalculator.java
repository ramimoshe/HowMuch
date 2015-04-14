package com.ramimos.howmuchcalculator.logic;

import java.util.ArrayList;

public class ProductsCalculator {
    private CurrencyType sourceCurrency;
    private ExchangeRate exchangeRate;

    public ProductsCalculator(CurrencyType sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
        exchangeRate = new ExchangeRate(CurrencyType.ILS);
        //exchangeRate.addSourceCurrency(sourceCurrency);
        exchangeRate.updateRates();
    }

    public Double calculatePrice(Double amount){
        Double rate = exchangeRate.getExchangeRate(sourceCurrency);
        return rate * amount;
    }

    public void setSourceCurrency(CurrencyType source){
        sourceCurrency = source;
        exchangeRate.updateRates();
    }
}