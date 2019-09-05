package id.app.io_asset_v01.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import id.app.io_asset_v01.activity.LoginActivity;

public class SessionManager {
    SharedPreferences pref;
    android.content.SharedPreferences.Editor editor1, editor2;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "userSession";
    public static final String IS_LOGIN = "status";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_MEMBERNAME = "membername";
    public static final String KEY_ROLE = "role";
    public static final String KEY_MEMBER_CODE = "memberCode";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_DEPARTMENT = "department";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor1 = pref.edit();
    }

    public void createLoginSession(String username) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_USERNAME, username);
        editor1.commit();

    }

    public void UpdateLoginSession(String imgUser) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_IMAGE, imgUser);
        editor1.commit();

    }

    /**
     * Create login session
     */
    public void createLoginSession(String username, String membername, String role, String memberCode, String email, String image, String level, String department) {
        editor1.putBoolean(IS_LOGIN, true);
        editor1.putString(KEY_USERNAME, username);
        editor1.putString(KEY_MEMBERNAME, membername);
        editor1.putString(KEY_ROLE, role);
        editor1.putString(KEY_MEMBER_CODE, memberCode);
        editor1.putString(KEY_EMAIL, email);
        editor1.putString(KEY_IMAGE, image);
        editor1.putString(KEY_LEVEL, level);
        editor1.putString(KEY_DEPARTMENT, department);
        editor1.commit();
    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        if (!this.isLoggedIn()) {
            editor1.putBoolean(IS_LOGIN, false);
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_MEMBERNAME, pref.getString(KEY_MEMBERNAME, null));
        user.put(KEY_ROLE, pref.getString(KEY_ROLE, null));
        user.put(KEY_MEMBER_CODE, pref.getString(KEY_MEMBER_CODE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_IMAGE, pref.getString(KEY_IMAGE, null));
        user.put(KEY_LEVEL, pref.getString(KEY_LEVEL, null));
        user.put(KEY_DEPARTMENT, pref.getString(KEY_DEPARTMENT, null));

        return user;
    }

    /**
     * Clear session details
     */
    public void backtoNews() {
        editor2.clear();
        editor2.commit();
    }

    public void logoutUser() {
        editor1.clear();
        editor1.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
