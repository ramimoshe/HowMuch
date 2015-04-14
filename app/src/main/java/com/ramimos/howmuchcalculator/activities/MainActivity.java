package com.ramimos.howmuchcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.ramimos.howmuchcalculator.R;
import com.ramimos.howmuchcalculator.logic.*;


public class MainActivity extends ActionBarActivity {

    ProductsCalculator productsCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerComponents();

        productsCalculator = new ProductsCalculator(getUserSourceCurrency());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        productsCalculator.setSourceCurrency(getUserSourceCurrency());
    }

    public void registerComponents(){
        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.inputAmount);
                TextView result = (TextView)findViewById(R.id.result);
                if (input.getText() == null){
                    showInvalidInputMessage();
                    return;
                }

                result.setText(productsCalculator.calculatePrice(Double.parseDouble(input.getText().toString())).toString());
            }
        });
    }

    private void showInvalidInputMessage() {
        Context context = getApplicationContext();
        CharSequence text = "קלט לא תקין";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent();
            intent.setClassName(this, "com.ramimos.howmuchcalculator.activities.MyPreferenceActivity");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public CurrencyType getUserSourceCurrency() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int enumNumber = prefs.getInt("sourceCurrency", CurrencyType.EUR.ordinal());

        return CurrencyType.values()[enumNumber];
    }
}
