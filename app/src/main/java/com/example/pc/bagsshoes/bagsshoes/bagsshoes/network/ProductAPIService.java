package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;


import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ProductAPIService {
    @GET("product")
    Observable<List<Product>> getProducts();
}
