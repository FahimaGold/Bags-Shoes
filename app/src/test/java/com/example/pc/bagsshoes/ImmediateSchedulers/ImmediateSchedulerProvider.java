package com.example.pc.bagsshoes.ImmediateSchedulers;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.providers.ISchedulerProvider;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ImmediateSchedulerProvider implements ISchedulerProvider {

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.newThread();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.newThread();
    }
}
