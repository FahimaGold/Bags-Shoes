package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.BottomNavigationHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.IBottomNavigationHelper;
import com.example.pc.bagsshoes.databinding.ActivityCartBinding;
import com.example.pc.bagsshoes.databinding.ActivityFavoritesBinding;

@AndroidEntryPoint
public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    private FavoritesFragment favoritesFragment;
    private IBottomNavigationHelper iBottomNavigationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityCartBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.cart_fragment_holder,new CartFragment())
                .commit();
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottom_navigation);
        iBottomNavigationHelper = new BottomNavigationHelper( this, bottomNavigationView );
        iBottomNavigationHelper.setOnBottomNavigationViewItemClick();

    }
}