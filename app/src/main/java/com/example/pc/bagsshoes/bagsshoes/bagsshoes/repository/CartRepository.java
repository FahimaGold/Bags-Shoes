package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.CartAPIService;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class CartRepository {
    private CartAPIService cartAPIService;

    @Inject
    public CartRepository(CartAPIService cartAPIService){
        this.cartAPIService = cartAPIService;
    }

    public Observable<List<Product>> getCartProducts(int id){
        return cartAPIService.getCartProducts(id);
    }
    public Observable<Map<String, String>> addProductToCart(Cart cart){
        return cartAPIService.addToCart( cart );
    }
}
