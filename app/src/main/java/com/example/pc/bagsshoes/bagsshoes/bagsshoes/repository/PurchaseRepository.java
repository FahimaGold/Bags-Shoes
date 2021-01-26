package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.PurchaseAPIService;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;

public class PurchaseRepository {

    private PurchaseAPIService purchaseAPIService;

    @Inject
    public PurchaseRepository(PurchaseAPIService purchaseAPIService){
        this.purchaseAPIService = purchaseAPIService;
    }

    public Response<ResponseBody> createEphemeralKey(HashMap<String, String> apiVersionMap){
        return purchaseAPIService.createEphemeralKey(apiVersionMap  );
    }

    public Response<ResponseBody> createPaymentIntent(HashMap<String, String> params){
        return purchaseAPIService.createPaymentIntent( params );
    }


}
