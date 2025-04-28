package com.example.task61;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.task61.fragment.ProfileFragment;
import com.example.task61.fragment.RecommendationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selected = null;
            if (item.getItemId() == R.id.menu_home) {
                selected = new RecommendationFragment();
            } else if (item.getItemId() == R.id.menu_profile) {
                selected = new ProfileFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }
}
