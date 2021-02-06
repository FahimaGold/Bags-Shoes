package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.UserCredentials;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AuthenticationRepository {
    private UserAPIService userAPIService;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public AuthenticationRepository(UserAPIService userAPIService, SharedPreferencesHelper sharedPreferencesHelper){
        this.userAPIService = userAPIService;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    public Observable<AuthenticationResponse> registerUser (User user){
        return userAPIService.registerUser( user );
    }

    public Observable<AuthenticationResponse> loginUser (UserCredentials userCredentials){
        return userAPIService.loginUser( userCredentials );
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

    public int getUserId(){
        return this.sharedPreferencesHelper.getUserId();
    }

    public void setUserId(int userId){
        this.sharedPreferencesHelper.setUserId( userId );
    }
}
