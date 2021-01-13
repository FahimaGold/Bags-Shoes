package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.ISharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRegistrationRepository {
    private UserAPIService userAPIService;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public UserRegistrationRepository(UserAPIService userAPIService, SharedPreferencesHelper sharedPreferencesHelper){
        this.userAPIService = userAPIService;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    public Observable<AuthenticationResponse> registerUser (User user){
        return userAPIService.registerUser( user );
    }

    public String getToken(){
        return this.sharedPreferencesHelper.getToken();
    }

    public void setToken(String token){
        this.sharedPreferencesHelper.setToken( token);
    }

    public boolean isLogin(){
        return this.sharedPreferencesHelper.isLogin();
    }

    public void setLogin(boolean login){
        this.sharedPreferencesHelper.setLogin( login );
    }
}
