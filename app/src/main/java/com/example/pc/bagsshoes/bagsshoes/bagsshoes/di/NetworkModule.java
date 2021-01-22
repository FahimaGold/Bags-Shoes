package com.example.pc.bagsshoes.bagsshoes.bagsshoes.di;


import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.CartAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(HttpLoggingInterceptor interceptor, SharedPreferencesHelper sharedPreferencesHelper) {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    // Add Auth Header
                    String token = sharedPreferencesHelper.getToken();
                    if(token == null) token = "";

                    Request request = chain.request().newBuilder().addHeader("Authorization","Bearer " +  token).build();
                    return chain.proceed(request);
                })
                .addInterceptor(interceptor)
                .build();

    }


    @Provides
    @Singleton
    public static ProductAPIService provideProductAPIService(OkHttpClient okHttpClient){

        return new Retrofit.Builder()
                .baseUrl( StringRProvider.BASE_URL +"api/v0/" )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductAPIService.class);
    }

    @Provides
    @Singleton
    public static UserAPIService provideUserAPIService(){

        return new Retrofit.Builder()
                .client( new OkHttpClient() )
                .baseUrl( StringRProvider.BASE_URL +"user/" )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(UserAPIService.class);
    }

    @Provides
    @Singleton
    public static CartAPIService provideCartAPIService(OkHttpClient okHttpClient){

        return new Retrofit.Builder()
                .baseUrl( StringRProvider.BASE_URL +"cart/" )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(CartAPIService.class);
    }
}
