package com.ramimos.howmuchcalculator.bl;

import java.util.Currency;
import java.util.HashMap;
import com.loopj.android.http.*;

import org.apache.http.Header;

/**
 * Created by ramimoshe on 4/9/15.
 */
public class ExchangeRate {


    private HashMap<Currency, Double> currencyCache;


    public ExchangeRate() {
        currencyCache = new HashMap<Currency, Double>();
    }

    public double getExchangeRate(Currency currency){
        return currencyCache.get(currency);
    }

    public void updateRates(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.freecurrencyconverterapi.com/api/v3/convert?q=USD_PHP&compact=y", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
