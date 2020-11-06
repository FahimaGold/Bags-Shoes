package com.example.pc.bagsshoes.bagsshoes.bagsshoes.di;

import android.app.Application;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.Database;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.ProductDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static Database provideDatabse(Application application){
        return Room.databaseBuilder(application, Database.class, "Bags_shoes")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Provides
    @Singleton
    public static ProductDao provideProductDao(Database database){
        return database.productDao();
    }

}
