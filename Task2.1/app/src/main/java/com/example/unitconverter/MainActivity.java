package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_convert);
        btn.setOnClickListener(v -> {
            EditText etInput = findViewById(R.id.et_input);
            Spinner spinnerFrom = findViewById(R.id.spinner_from);
            Spinner spinnerTo = findViewById(R.id.spinner_to);
            TextView tvResult = findViewById(R.id.tv_result);

            try {
                double input = Double.parseDouble(etInput.getText().toString());
                String from = spinnerFrom.getSelectedItem().toString();
                String to = spinnerTo.getSelectedItem().toString();

                double result = UnitConverter.convert(input, from, to);
                tvResult.setText(String.format("%.2f", result));

            } catch (Exception e) {
                tvResult.setText("wrong");
            }
        });
    }
}