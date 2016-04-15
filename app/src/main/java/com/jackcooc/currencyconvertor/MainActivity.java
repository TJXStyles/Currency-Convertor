package com.jackcooc.currencyconvertor;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_from;
    private Spinner spinner_to;
    private Button convert_button;
    private EditText currency;
    private TextView text1;
    private TextView text2;
    private String myJson;


    private String base_url = "http://api.fixer.io/";
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(this);

        currency = (EditText) findViewById(R.id.dollarField);
        spinner_from = (Spinner) findViewById(R.id.spinner_from);
        spinner_to = (Spinner) findViewById(R.id.spinner_to);
        convert_button = (Button) findViewById(R.id.convertButton);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_from.setAdapter(adapter);
        spinner_to.setAdapter(adapter);

        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currency.getText().toString().length() < 1) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                } else {
                    final Double currency_from_value = Double.valueOf(currency.getText().toString());
                    String from_currency = String.valueOf(spinner_from.getSelectedItem());
                    final String to_currency = String.valueOf(spinner_to.getSelectedItem());

                    String url = base_url + "latest?base=" + from_currency;

                    aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            if (json != null) {
                                //successful ajax call, show status code and json content
//                                Toast.makeText(aq.getContext(), status.getCode() + ":" + json.toString(), Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject item1 = json.getJSONObject("rates");
                                    double rate = item1.getDouble(to_currency.toString());
//                                    JSONObject current_currency = json.getJSONObject()
//                                    Double rate = json.getDouble(to_currency.toString());
//                                    Toast.makeText(getBaseContext(),""+ rate, Toast.LENGTH_LONG).show();

                                    double the_result = currency_from_value * rate;
                                    text1.setText(currency.getText().toString() + " " + spinner_from.getSelectedItem().toString() + "=");
                                    text2.setText(String.valueOf(the_result) + " " + spinner_to.getSelectedItem().toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                //ajax error, show error code
                                Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    Log.i ("text1", text1.toString());

                }
            }
        });
    }

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