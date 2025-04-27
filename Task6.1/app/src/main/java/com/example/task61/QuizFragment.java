package com.example.task61;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.task61.databinding.FragmentQuizBinding;
import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {

    FragmentQuizBinding binding;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private List<Question> questionList;

    static class Question {
        String questionText;
        String[] options;
        int correctIndex;

        Question(String questionText, String[] options, int correctIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);

        initQuestions();
        loadQuestion();

        binding.btnSubmit.setOnClickListener(view -> {
            int selectedId = binding.radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedButton = binding.getRoot().findViewById(selectedId);
            int selectedIndex = binding.radioGroup.indexOfChild(selectedButton);
            if (selectedIndex == questionList.get(currentQuestionIndex).correctIndex) {
                score++;
                Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Wrong!", Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                loadQuestion();
            } else {
                binding.questionText.setText("Quiz Completed! Your score: " + score + "/" + questionList.size());
                binding.radioGroup.setVisibility(View.GONE);
                binding.btnSubmit.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }

    private void initQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of Australia?",
                new String[]{"Sydney", "Canberra", "Melbourne", "Perth"}, 1));
        questionList.add(new Question("2 + 2 = ?", new String[]{"3", "4", "5", "6"}, 1));
        questionList.add(new Question("Who developed Java?", new String[]{"Microsoft", "Sun Microsystems", "Apple", "IBM"}, 1));
    }

    private void loadQuestion() {
        Question q = questionList.get(currentQuestionIndex);
        binding.questionText.setText(q.questionText);
        for (int i = 0; i < 4; i++) {
            ((RadioButton) binding.radioGroup.getChildAt(i)).setText(q.options[i]);
        }
        binding.radioGroup.clearCheck();
    }
}

