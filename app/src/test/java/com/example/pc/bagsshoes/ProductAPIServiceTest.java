package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.providers.StringRProvider;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAPIServiceTest {


    private ProductAPIService productAPIService;

    @Before
    public void createService(){
        productAPIService = new Retrofit.Builder()
                .baseUrl( StringRProvider.BASE_URL +"api/v0/" )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductAPIService.class);
    }

    @Test
    public void getProductsTest()  {

        List<Product> list = new ArrayList<>();

        Product p = new Product( 7, "Michael Kors", 7200, "128-3001451-30S7GEZB1V_BROWN_M.jpg", "Bag", "BAG");
        list.add( p );
        p = new Product( 5, "Aldo", 14000, "81iz2-k2wQL._AC_UL320_.jpg", "Her Aldo Heels", "SHOE");
        list.add( p );
        p = new Product( 4, "Clark", 13500, "10143116_500_A.jpg", "His Clark Shoes", "SHOE");
        list.add( p );
        p = new Product( 3, "Gucci", 9200, "gucci-gg-supreme-padlock-shoulder-bag_15048824_25845506_2048.jpg", "Gucci Bag", "BAG");
        list.add( p );
        p = new Product( 2, "Michael Kors", 8000, "238516590.jpg", "Michael Kors Purse", "BAG");
        list.add( p );
        p = new Product( 1, "Aldo", 12500, "s-l225.jpg", "Her Aldo Shoes", "SHOE");
        list.add( p );


        TestObserver<List<Product>> testObserver = new TestObserver<>();
        productAPIService.getProducts().subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValue(list);

    }
}
