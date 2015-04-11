package com.ramimos.howmuchcalculator.bl;

import java.util.ArrayList;

public class ProductsCalculator {
    private ArrayList<CurrencyType> targetCurrencies;
    private ExchangeRate exchangeRate;

    public ProductsCalculator(CurrencyType targetCurrency) {
        this.targetCurrencies = new ArrayList<CurrencyType>();
        this.targetCurrencies.add(targetCurrency);
        exchangeRate = new ExchangeRate(CurrencyType.EUR);
        exchangeRate.addTargetCurrency(targetCurrency);
        exchangeRate.updateRates();
    }

    public Double calculatePrice(Double amount){
        Double rate = exchangeRate.getExchangeRate(targetCurrencies.get(0));
        return rate * amount;
    }
}