package com.example.task51c;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.task51c.fragment.HomeFragment;
import com.example.task51c.fragment.VideoPlayerFragment;

public class MainActivity extends AppCompatAc   tivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selected = null;
            switch (item.getItemId()) {
                case R.id.nav_news:
                    selected = new HomeFragment();
                    break;
                case R.id.nav_itube:
                    selected = new VideoPlayerFragment();
                    break;
            }
            return loadFragment(selected);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}
