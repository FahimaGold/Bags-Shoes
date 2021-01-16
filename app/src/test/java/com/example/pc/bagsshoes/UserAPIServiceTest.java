package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.UserCredentials;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertTrue;

public class UserAPIServiceTest {

    private UserAPIService userAPIService;
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
    public void registerUserTest(){
        //Expected response on a successful registration
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("zadff444ssfrz", "");

        //Schedule the expected response on a successful registration
        server.enqueue( new MockResponse().setBody(new Gson().toJson( authenticationResponse )  ));

        //Using a mock url
        userAPIService = new Retrofit.Builder()
                .baseUrl( server.url( "/" ) )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(UserAPIService.class);
        User user = new User("some.email@example.com", "firtname", "surname", "0770369971", "somePassword");
        TestObserver<AuthenticationResponse> testObserver = new TestObserver<>();
        userAPIService.registerUser( user ).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertComplete();

    }


    @Test
    public void loginUserTest(){

        AuthenticationResponse authenticationResponse = new AuthenticationResponse("zadff444ssfrz", "");
        server.enqueue( new MockResponse().setBody(new Gson().toJson( authenticationResponse )  ));
        userAPIService = new Retrofit.Builder()
                .baseUrl( server.url( "/" ) )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .build()
                .create(UserAPIService.class);
        UserCredentials userCredentials = new UserCredentials("some.email@example.com","somePassword");
        TestObserver<AuthenticationResponse> testObserver = new TestObserver<>();
        userAPIService.loginUser( userCredentials ).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertComplete();

    }



}
