package com.example.pc.bagsshoes.bagsshoes.bagsshoes.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static ProductAPIService provideProductAPIService(){

        return new Retrofit.Builder()
                .baseUrl( StringRProvider.BASE_URL +"api/v0/" )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductAPIService.class);
    }

    @Provides
    @Singleton
    public static UserAPIService provideUserAPIService(){

        return new Retrofit.Builder()
                .baseUrl( StringRProvider.BASE_URL +"user/" )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(UserAPIService.class);
    }
}
