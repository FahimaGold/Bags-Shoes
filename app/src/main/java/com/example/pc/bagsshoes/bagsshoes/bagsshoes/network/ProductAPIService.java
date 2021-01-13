package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;


import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductAPIService {
    @GET("product")
    Observable<List<Product>> getProducts();

}
