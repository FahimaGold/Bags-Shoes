package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAPIServiceTest {


    private ProductAPIService productAPIService;
    private MockWebServer server;

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start();

    }

    @After
    public void clear() throws IOException {
        server.shutdown();
    }
    @Test
    public void getProductsSuccessTest()  {

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

        //Scheduling the expected response
        server.enqueue( new MockResponse().setBody(new Gson().toJson( list)  ));

        //Setting a mock url
        productAPIService = new Retrofit.Builder()
                .baseUrl( server.url( "/" ) )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductAPIService.class);
        TestObserver<List<Product>> testObserver = new TestObserver<>();
        productAPIService.getProducts().subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValue(list);

    }


}
