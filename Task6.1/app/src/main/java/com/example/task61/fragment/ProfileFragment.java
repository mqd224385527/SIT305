package com.example.task61.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.task61.R;
import com.example.task61.activity.QuizActivity;
import com.example.task61.utils.SessionManager;

public class ProfileFragment extends Fragment {

    TextView tvEmail;
    Button btnQuiz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvEmail = view.findViewById(R.id.tvEmail);
        btnQuiz = view.findViewById(R.id.btnQuiz);

        // 显示当前登录用户
        SessionManager sm = new SessionManager(getContext());
        tvEmail.setText("Logged in as: " + sm.getUser());

        // 匿名内部类替换 lambda
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
