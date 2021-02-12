package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.BottomNavigationHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.IBottomNavigationHelper;
import com.example.pc.bagsshoes.databinding.ActivityFavoritesBinding;

@AndroidEntryPoint
public class FavoritesActivity extends AppCompatActivity {

    private ActivityFavoritesBinding binding;
    private FavoritesFragment favoritesFragment;
    private IBottomNavigationHelper iBottomNavigationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityFavoritesBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.favorites_fragment_holder,new FavoritesFragment())
                .commit();

        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottom_navigation);
        iBottomNavigationHelper = new BottomNavigationHelper( this, bottomNavigationView );
        iBottomNavigationHelper.setOnBottomNavigationViewItemClick();
    }
}