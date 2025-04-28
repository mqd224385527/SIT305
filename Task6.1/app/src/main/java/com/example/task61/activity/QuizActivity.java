package com.example.task61.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task61.R;

public class QuizActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnSubmitQuiz;
    TextView txtFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        radioGroup = findViewById(R.id.radioGroup);
        btnSubmitQuiz = findViewById(R.id.btnSubmitQuiz);
        txtFeedback = findViewById(R.id.txtFeedback);

        btnSubmitQuiz.setOnClickListener(v -> {
            int selected = radioGroup.getCheckedRadioButtonId();
            if (selected == R.id.optionB) {
                txtFeedback.setText("Correct! Well done.");
            } else {
                txtFeedback.setText("Incorrect. Try reviewing the materials.");
            }
        });
    }
}
