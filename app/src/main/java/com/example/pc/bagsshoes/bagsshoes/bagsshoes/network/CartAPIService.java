package com.example.pc.bagsshoes.bagsshoes.bagsshoes.network;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartAPIService {
    @GET("{id}")
    Observable<List<Product>> getCartProducts(@Path("id") int id);

    @POST("add")
    Observable<Map<String, String>> addToCart(@Body Cart cart);
}
