package com.jackcooc.currencyconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        EditText dollarField = (EditText) findViewById(R.id.dollarField);
        Double dollarAmount = 0.0;

        final String myStr = dollarField.getText().toString();
        if (!myStr.isEmpty()) {
            dollarAmount = Double.parseDouble(myStr);
            TextView convertedAmount = (TextView) findViewById(R.id.total);
            convertedAmount.setText("" + calculateConversion(dollarAmount));
        } else {
            Toast.makeText(this, R.string.no_value, Toast.LENGTH_SHORT).show();
        }

    }

    private Double calculateConversion(Double amount) {
        Double euroAmount = 1.68;
        amount *= euroAmount;
        return amount;
    }

}
