package com.ramimos.howmuchcalculator.providers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ramimos.howmuchcalculator.logic.CurrencyType;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramimoshe on 4/12/15.
 */
public class FreeCurrencyConverterProvier {
    private String serverUrlFormat = "http://www.freecurrencyconverterapi.com/api/v3/convert?q=%s&compact=ultra";
    private HashMap<CurrencyType, Double> currencySourcesCache;
    private CurrencyType targetCurrency;

    public FreeCurrencyConverterProvier(CurrencyType target) {
        currencySourcesCache = new HashMap<CurrencyType, Double>();
        targetCurrency = target;
    }

    public void addSourceCurrency(CurrencyType source){
        currencySourcesCache.put(source, 0d);
    }

    public double getExchangeRate(CurrencyType sourceCurrency){
        return currencySourcesCache.get(sourceCurrency);
    }

    public void updateRates(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getAllCurrencyUrl(), new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.i("com.trumphurst", "Start");
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Log.i("com.trumphurst", "Suc");
                updateExchangeRatesCache(response);

                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Log.i("com.trumphurst", "Failure");
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                Log.i("com.trumphurst", "Ret");
            }
        });
    }

    private void updateExchangeRatesCache(byte[] response) {
        String jsonString = new String(response);
        JsonElement jsonElement = new Gson().fromJson(new String(response), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for(Map.Entry<String, JsonElement> rate : jsonObject.entrySet()){
            Log.i("com", rate.getKey().toString());
            Log.i("com", rate.getValue().toString());

            String currencyKey = rate.getKey().toString().split("_")[0];

            currencySourcesCache.put(CurrencyType.valueOf(currencyKey),Double.parseDouble(rate.getValue().toString()));
        }
    }

    private String getAllCurrencyUrl(){
        String query = buildQueryOfAllCurrency();
        return String.format(serverUrlFormat, query);
    }

    public String buildQueryOfAllCurrency(){
        StringBuilder sb = new StringBuilder();
        for (CurrencyType currency : CurrencyType.values()){
            sb.append(currency.name());
            sb.append("_");
            sb.append(targetCurrency.name());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
