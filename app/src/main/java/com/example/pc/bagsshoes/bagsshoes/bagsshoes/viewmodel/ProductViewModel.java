package com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel;

import android.util.Log;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import java.util.ArrayList;
import java.util.List;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


import com.example.pc.bagsshoes.bagsshoes.bagsshoes.providers.SchedulerProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductRepository;

public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<ArrayList<Product>> productList = new MutableLiveData<>();

    @ViewModelInject
    public ProductViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public MutableLiveData<ArrayList<Product>> getProductList(){
        return productList;
    }

    public void getProducts(){

        final ArrayList<Product> li = new ArrayList<>();
        productRepository.getProducts()
                .subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<List<Product>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onNext(@NonNull List<Product> products) {

                        productList.setValue( ( ArrayList<Product> ) products );
                    }



                    @Override
                    public void onError(@NonNull Throwable e) {
                              Log.e("ERREUR","is " + e);
                    }

                    @Override
                    public void onComplete() {

                        productList.setValue( li );
                    }
                } );

    }
}
