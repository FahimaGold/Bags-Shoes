package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.ProductDao;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import javax.inject.Inject;


import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;

import java.util.List;

import io.reactivex.Observable;


public class ProductRepository {
    private ProductAPIService productAPIService;


    @Inject
    public ProductRepository(ProductAPIService productAPIService){
        this.productAPIService = productAPIService;
    }


    public Observable<List<Product>> getProducts(){
        return productAPIService.getProducts();
    }
}

