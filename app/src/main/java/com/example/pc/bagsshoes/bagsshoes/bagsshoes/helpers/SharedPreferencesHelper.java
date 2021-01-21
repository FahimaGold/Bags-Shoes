package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SharedPreferencesHelper implements ISharedPreferencesHelper{
    private SharedPreferences prefs;
    private final static String TOKEN = "token";
    private final static  String LOGIN = "login";
    private final static String USER_ID = "userId";


    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context) {
        this.prefs = context.getSharedPreferences( "prefs", Context.MODE_PRIVATE );
    }



    @Override
    public String getToken() {
        return prefs.getString( TOKEN, "" );
    }

    @Override
    public void setToken(String token) {
        SharedPreferences.Editor mEditor = prefs.edit();
        mEditor.putString(TOKEN, token);
        mEditor.apply();

    }

    @Override
    public boolean isLogin() {
        return prefs.getBoolean( LOGIN, false );
    }

    @Override
    public void setLogin(boolean login) {
        SharedPreferences.Editor mEditor = prefs.edit();
        mEditor.putBoolean(LOGIN, login);
        mEditor.apply();

    }

    @Override
    public int getUserId() {
        return prefs.getInt( USER_ID, 0);
    }

    @Override
    public void setUserId(int userId) {
        SharedPreferences.Editor mEditor = prefs.edit();
        mEditor.putInt(USER_ID, userId);
        mEditor.apply();
    }
}
