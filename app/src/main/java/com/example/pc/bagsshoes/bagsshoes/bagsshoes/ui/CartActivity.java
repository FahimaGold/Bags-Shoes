package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.databinding.ActivityCartBinding;
import com.example.pc.bagsshoes.databinding.ActivityFavoritesBinding;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityCartBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.cart_fragment_holder,new CartFragment())
                .commit();

    }
}