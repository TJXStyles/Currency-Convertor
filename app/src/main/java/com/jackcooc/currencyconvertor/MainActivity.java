package com.jackcooc.currencyconvertor;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetWebPageTask().execute("http://api.fixer.io/latest?symbols=USD");
//    }
//
//    public void convert(View view) {
//        EditText dollarField = (EditText) findViewById(R.id.dollarField);
//        Double dollarAmount = 0.0;
//
//        final String myStr = dollarField.getText().toString();
//        if (!myStr.isEmpty()) {
//            dollarAmount = Double.parseDouble(myStr);
//            TextView convertedAmount = (TextView) findViewById(R.id.total);
//            convertedAmount.setText("" + calculateConversion(dollarAmount));
//        } else {
//            Toast.makeText(this, R.string.no_value, Toast.LENGTH_SHORT).show();
//        }
//
//    }

//    private Double calculateConversion(Double amount) {
//        Double euroAmount = 1.68;
//        amount *= euroAmount;
//        return amount;
//    }


    }

    // Get URL address and gets contents as a String
    // http://api.fixer.io/latest

    private String getWebsite(String address) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;

        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    public class GetWebPageTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {

            Log.i("String", result);

            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected String doInBackground(String... url) {
            return getWebsite(url[0]);
        }
    }
}