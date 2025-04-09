package com.example.task31c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;

    private Question[] questions = {
        new Question("What is Android?", new String[]{"OS", "Language", "Database", "Protocol"}, 0),
        new Question("What is Kotlin?", new String[]{"OS", "Language", "Database", "Protocol"}, 1),
        new Question("What is Android Studio?", new String[]{"OS", "IDE", "Database", "Protocol"}, 1),
        new Question("What is Java?", new String[]{"OS", "Language", "Database", "Protocol"}, 1),
        new Question("What is XML?", new String[]{"Markup", "Language", "Database", "Protocol"}, 0)
    };

    private int currentQuestionIndex = 0;
    private int score = 0;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("USER_NAME");

        progressBar = findViewById(R.id.progressBar);
        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        progressBar.setMax(questions.length);
        displayQuestion();

        submitButton.setOnClickListener(v -> checkAnswer());
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question question = questions[currentQuestionIndex];
            questionTextView.setText(question.getText());

            answerRadioGroup.removeAllViews();
            for (int i = 0; i < question.getOptions().length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setId(i);
                radioButton.setText(question.getOptions()[i]);
                answerRadioGroup.addView(radioButton);
            }

            progressBar.setProgress(currentQuestionIndex);
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL_QUESTIONS", questions.length);
            intent.putExtra("USER_NAME", userName);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        int correctAnswerIndex = questions[currentQuestionIndex].getCorrectAnswerIndex();

        if (selectedId == correctAnswerIndex) {
            selectedRadioButton.setTextColor(Color.GREEN);
            score++;
        } else {
            selectedRadioButton.setTextColor(Color.RED);
            ((RadioButton) findViewById(correctAnswerIndex)).setTextColor(Color.GREEN);
        }

        submitButton.setEnabled(false);
        answerRadioGroup.setEnabled(false);

        new Handler().postDelayed(() -> {
            currentQuestionIndex++;
            answerRadioGroup.clearCheck();
            submitButton.setEnabled(true);
            answerRadioGroup.setEnabled(true);
            displayQuestion();
        }, 1500);
    }
}

class Question {
    private String text;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String text, String[] options, int correctAnswerIndex) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
} 