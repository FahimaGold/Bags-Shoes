package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import java.util.HashMap;
import java.util.Observable;


import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PurchaseAPIService {
    @FormUrlEncoded
    @POST("ephemeral_keys")
    Response<ResponseBody> createEphemeralKey(@FieldMap HashMap<String, String> apiVersionMap);

    @FormUrlEncoded
    @POST("create_payment_intent")
    Response<ResponseBody> createPaymentIntent(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("create_setup_intent")
    Response<ResponseBody> createSetupIntent(@FieldMap  HashMap<String, String> params);
}
