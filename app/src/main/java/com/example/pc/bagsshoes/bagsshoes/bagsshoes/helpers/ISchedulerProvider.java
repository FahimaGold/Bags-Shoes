package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;


public interface ISchedulerProvider {

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
