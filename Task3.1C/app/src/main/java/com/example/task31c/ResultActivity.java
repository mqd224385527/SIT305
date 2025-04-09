package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private Button takeNewQuizButton;
    private Button finishButton;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        userName = getIntent().getStringExtra("USER_NAME");

        scoreTextView = findViewById(R.id.scoreTextView);
        takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        finishButton = findViewById(R.id.finishButton);

        scoreTextView.setText("Your score: " + score + "/" + totalQuestions);

        takeNewQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.putExtra("USER_NAME", userName);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> finishAffinity());
    }
} 