package com.example.task61;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 用 setContentView 而不是 binding

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new QuizFragment(); // 你改成对应 fragment
                    break;
                case R.id.menu_quiz:
                    selectedFragment = new QuizFragment(); // 示例
                    break;
                case R.id.menu_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });

        // 默认显示
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }
}
