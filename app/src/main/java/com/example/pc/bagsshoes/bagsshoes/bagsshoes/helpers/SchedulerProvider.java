package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SchedulerProvider implements ISchedulerProvider{
    @Nullable
    private static SchedulerProvider INSTANCE;

    //Prevent direct instantiation

    private SchedulerProvider(){

    }
    public static SchedulerProvider getInstance(){
        if(INSTANCE == null)
            INSTANCE = new SchedulerProvider();
        return INSTANCE;
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}

