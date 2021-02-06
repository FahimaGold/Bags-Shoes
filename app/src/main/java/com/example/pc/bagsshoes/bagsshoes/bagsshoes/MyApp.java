package com.example.pc.bagsshoes.bagsshoes.bagsshoes;

import android.app.Application;
import com.stripe.android.PaymentConfiguration;
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51IDFQGHAaQu8zZLyyxvZjl5AJmPSNGiXGa9bXSyNJbTzZKP8CTPc53xk8ZCzgwBAibDyTcNt0FitFSJbAoIhnXPa00nanjbYxv"
        );
    }
}
