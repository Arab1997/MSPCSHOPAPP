package com.ukuya.mspc.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ukuya.mspc.MyApp;
import com.ukuya.mspc.model.User;


import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtil {

    private static final String PREFS_CODE = "mcps_app";
    private static final String PREF_USER = "pref_user";
    private MyApp mApp;
    private Gson gson;


    public PreferenceUtil(MyApp mApp, Gson gson) {
        this.mApp = mApp;
        this.gson = gson;
    }

    public SharedPreferences.Editor getEditor() {
        return getCodePreferences().edit();
    }

    public SharedPreferences.Editor getEditor(String name) {
        if (TextUtils.isEmpty(name)) {
            return getCodePreferences().edit();
        }
        return getPreferences(name).edit();
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(PREF_USER, gson.toJson(user));
        save(editor);
    }

    public User getUser() {
        try {
            return gson.fromJson(getCodePreferences().getString(PREF_USER,
                    gson.toJson(new User())), User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        }
    }

    private SharedPreferences getPreferences(String name) {
        return mApp.getSharedPreferences(name, MODE_PRIVATE);
    }

    public boolean clearAll() {
        SharedPreferences.Editor editor = getCodePreferences().edit();
        editor.clear();
        save(editor);
        return true;
    }

    public SharedPreferences getCodePreferences() {
        return mApp.getSharedPreferences(PREFS_CODE, MODE_PRIVATE);
    }

    private static void save(final SharedPreferences.Editor editor) {
        editor.apply();
    }
}
