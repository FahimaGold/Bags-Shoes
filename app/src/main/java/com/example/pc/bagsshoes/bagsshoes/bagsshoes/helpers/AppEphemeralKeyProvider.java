package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

import android.content.Context;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.CartViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.PurchaseViewModel;
import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.disposables.CompositeDisposable;

public class AppEphemeralKeyProvider implements EphemeralKeyProvider {

    private PurchaseViewModel purchaseViewModel;
    private final CompositeDisposable compositeDisposable =
            new CompositeDisposable();


    public AppEphemeralKeyProvider(PurchaseViewModel purchaseViewModel){
       this.purchaseViewModel = purchaseViewModel;
    }

    @Override
    public void createEphemeralKey(@NotNull String apiVersion, @NotNull EphemeralKeyUpdateListener ephemeralKeyUpdateListener) {
        final Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put("api_version", apiVersion);
        purchaseViewModel.createEphemeralKey( ( HashMap<String, String> ) apiParamMap );
        final String rawKey = purchaseViewModel.getResponse().toString();
        ephemeralKeyUpdateListener.onKeyUpdate(rawKey);
        // Using RxJava2 for handling asynchronous responses

      /*  compositeDisposable.add(backendApi.createEphemeralKey(apiParamMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            try {
                                final String rawKey = response.string();
                                keyUpdateListener.onKeyUpdate(rawKey);
                            } catch (IOException ignored) {
                            }
                        }));*/

    }
}
