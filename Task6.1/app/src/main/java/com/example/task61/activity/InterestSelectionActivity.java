package com.example.task61.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task61.MainActivity;
import com.example.task61.R;
import com.example.task61.utils.SessionManager;

import java.util.ArrayList;

public class InterestSelectionActivity extends AppCompatActivity {

    CheckBox chkAI, chkMath, chkScience, chkHistory;
    Button btnSubmitInterest;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_selection);

        chkAI = findViewById(R.id.chkAI);
        chkMath = findViewById(R.id.chkMath);
        chkScience = findViewById(R.id.chkScience);
        chkHistory = findViewById(R.id.chkHistory);
        btnSubmitInterest = findViewById(R.id.btnSubmitInterest);

        sessionManager = new SessionManager(this);

        btnSubmitInterest.setOnClickListener(v -> {
            ArrayList<String> interests = new ArrayList<>();
            if (chkAI.isChecked()) interests.add("AI");
            if (chkMath.isChecked()) interests.add("Math");
            if (chkScience.isChecked()) interests.add("Science");
            if (chkHistory.isChecked()) interests.add("History");

            sessionManager.saveInterest(interests.toString()); // store dummy
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
