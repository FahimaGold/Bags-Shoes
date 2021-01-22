package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.CartViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductDetailViewModel;
import com.example.pc.bagsshoes.databinding.ActivityHomeBinding;
import com.example.pc.bagsshoes.databinding.ActivityProductBinding;
import com.example.pc.bagsshoes.databinding.FragmentProductBinding;

@AndroidEntryPoint
public class ProductActivity extends AppCompatActivity {

    public static ActivityProductBinding activityProductBinding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityProductBinding = ActivityProductBinding.inflate( getLayoutInflater() );
        setContentView( activityProductBinding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.product_fragment_holder, new ProductFragment())
                .commit();
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) activityProductBinding.productBottomNavigation;


    }




}