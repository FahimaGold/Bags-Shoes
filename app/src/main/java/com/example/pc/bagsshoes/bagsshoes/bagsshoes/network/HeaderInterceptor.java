package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public HeaderInterceptor(SharedPreferencesHelper sharedPreferencesHelper){

    }
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader("Authorization", sharedPreferencesHelper.getToken());

        return chain.proceed(requestBuilder.build());
    }
}
