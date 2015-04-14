package com.ramimos.howmuchcalculator.logic;

import com.ramimos.howmuchcalculator.providers.FreeCurrencyConverterProvier;

/**
 * Created by ramimoshe on 4/9/15.
 */
public class ExchangeRate {

    FreeCurrencyConverterProvier freeCurrencyConverterProvier;
    public ExchangeRate(CurrencyType target) {
        freeCurrencyConverterProvier = new FreeCurrencyConverterProvier(target);
        freeCurrencyConverterProvier.updateRates();
    }

    //public void addSourceCurrency(CurrencyType source){
    //    freeCurrencyConverterProvier.addSourceCurrency(source);
    //}

    public void updateRates() {
        freeCurrencyConverterProvier.updateRates();
    }

    public Double getExchangeRate(CurrencyType sourceCurrencyType) {
        return freeCurrencyConverterProvier.getExchangeRate(sourceCurrencyType);
    }
}
