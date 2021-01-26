package com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.PurchaseRepository;

import java.util.HashMap;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PurchaseViewModel extends ViewModel {

    private PurchaseRepository purchaseRepository;
    private MutableLiveData<Response<ResponseBody>> response =  new MutableLiveData<>();

    public Response<ResponseBody> getResponse(){
        return response.getValue();
    }
    @ViewModelInject
    public PurchaseViewModel(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }


    public void createEphemeralKey(HashMap<String, String> apiVersionMap){
          response.setValue( purchaseRepository.createEphemeralKey( apiVersionMap ) );

    }
}
