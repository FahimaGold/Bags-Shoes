package com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel;


import android.util.JsonReader;
import android.util.Log;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SchedulerProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.CartRepository;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class CartViewModel extends ViewModel {
    private CartRepository cartRepository;
    private MutableLiveData<ArrayList<Product>> cartProducts = new MutableLiveData<>();
    private MutableLiveData<String> addingResponse = new MutableLiveData<>();

    @ViewModelInject
    public CartViewModel(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public MutableLiveData<ArrayList<Product>> getProducts(){
        return cartProducts;
    }

    public MutableLiveData<String> getProductAddedResponse(){
        return addingResponse;
    }

    public void addProductToCart(Cart cart) {
        cartRepository.addProductToCart( cart )
                .subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<Map<String, String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Map<String, String> response) {

                            addingResponse.setValue( response.get( "response" ) );

                        Log.i("cartRes","is " + addingResponse.getValue());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Error ","Error at " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    public void getCartProducts(int id){
        cartRepository.getCartProducts( id )
                .subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                         cartProducts.setValue( ( ArrayList<Product> ) products );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.e("Error ","Error at " + e);


                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }
}
