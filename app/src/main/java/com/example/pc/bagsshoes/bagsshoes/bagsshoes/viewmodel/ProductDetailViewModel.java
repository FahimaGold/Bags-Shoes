package com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel;

import android.util.Log;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.providers.SchedulerProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductDetailRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class ProductDetailViewModel extends ViewModel {
    private ProductDetailRepository productDetailRepository;
    private MutableLiveData<ArrayList<Product>> favoriteProductList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isProductFavorite = new MutableLiveData<>();

    @ViewModelInject
    public ProductDetailViewModel(ProductDetailRepository productDetailRepository){
        this.productDetailRepository = productDetailRepository;
    }

    public MutableLiveData<ArrayList<Product>> getFavoriteProductList(){
        return favoriteProductList;
    }

    public MutableLiveData<Boolean> checkFavorite(){
        return isProductFavorite;
    }

    public void getFavoriteProducts(){

        productDetailRepository.getFavoriteProducts()
                .subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<List<Product>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }


                    @Override
                    public void onNext(@NonNull List<Product> products) {

                        favoriteProductList.setValue( ( ArrayList<Product> ) products );
                    }



                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ERREUR","is " + e);
                    }

                    @Override
                    public void onComplete() {


                    }
                } );


    }

    public void  addProductToFavorites(Product product){
        Observable<Product> observable;
        observable = io.reactivex.Observable.just( product );
        observable.subscribeOn( SchedulerProvider.getInstance().io() )
                .subscribe( new Observer<Product>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Product product) {
                        productDetailRepository.addProductToFavorites( product );

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );

    }

    public void isFavorite(long id) {

        productDetailRepository.isFavorite(id).subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                       isProductFavorite.setValue( aBoolean );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }


    public void deleteProductFromFavorites(long id){
           Observable<Long> observable = Observable.just( id );
           observable.subscribeOn( SchedulerProvider.getInstance().io() )
                   .subscribe( new Observer<Long>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {

                       }

                       @Override
                       public void onNext(@NonNull Long aLong) {
                           productDetailRepository.deleteProductFromFavorites( aLong );

                       }

                       @Override
                       public void onError(@NonNull Throwable e) {

                       }

                       @Override
                       public void onComplete() {

                       }
                   } );



    }
}
