package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

public interface ISharedPreferencesHelper {
    String getToken();
    void setToken(String token);
    boolean isLogin();
    void setLogin(boolean login);
}
