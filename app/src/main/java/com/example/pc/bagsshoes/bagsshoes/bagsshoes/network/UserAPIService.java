package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPIService {

    @POST("auth")

    Observable<AuthenticationResponse> registerUser(@Body User user);
}
