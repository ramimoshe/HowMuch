package com.ramimos.howmuchcalculator.logic;

import java.util.ArrayList;

public class ProductsCalculator {
    private ArrayList<CurrencyType> sourceCurrencies;
    private ExchangeRate exchangeRate;

    public ProductsCalculator(CurrencyType sourceCurrency) {
        this.sourceCurrencies = new ArrayList<CurrencyType>();
        this.sourceCurrencies.add(sourceCurrency);
        exchangeRate = new ExchangeRate(CurrencyType.ILS);
        exchangeRate.addSourceCurrency(sourceCurrency);
        exchangeRate.updateRates();
    }

    public Double calculatePrice(Double amount){
        Double rate = exchangeRate.getExchangeRate(sourceCurrencies.get(0));
        return rate * amount;
    }

    public void setSourceCurrency(CurrencyType source){
        exchangeRate.addSourceCurrency(source);
        exchangeRate.updateRates();
    }
}