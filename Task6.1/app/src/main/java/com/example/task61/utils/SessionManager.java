package com.example.task61.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "learning_app";
    private static final String KEY_USER = "user_email";

    SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(String email) {
        prefs.edit().putString(KEY_USER, email).apply();
    }

    public String getUser() {
        return prefs.getString(KEY_USER, "Guest");
    }
    public void saveInterest(String interests) {
        prefs.edit().putString("user_interests", interests).apply();
    }

    public String getInterest() {
        return prefs.getString("user_interests", "None");
    }

}
