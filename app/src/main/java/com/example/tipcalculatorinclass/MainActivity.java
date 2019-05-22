package com.example.tipcalculatorinclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private double bill = 0.0;
    private double percent = 0.15;

    private TextView billAmountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            bill = Double.parseDouble(s.toString());
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            calculate();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void calculate(){
        percentTextView.setText(percentFormat.format(percent));

        double tip = bill * percent;
        double total = bill + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountTextView = findViewById(R.id.billAmountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        EditText amountEditText = findViewById(R.id.billAmountTextView);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekbar = findViewById(R.id.seekBar);
        percentSeekbar.setOnSeekBarChangeListener(seekbarListener);

    }
}
