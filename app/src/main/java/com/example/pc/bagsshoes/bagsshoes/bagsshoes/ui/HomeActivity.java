package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;


import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters.CartAdapter;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.BottomNavigationHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.IBottomNavigationHelper;
import com.example.pc.bagsshoes.databinding.ActivityHomeBinding;


@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {


    private ActivityHomeBinding binding;
    public com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView;
    private IBottomNavigationHelper iBottomNavigationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityHomeBinding.inflate( getLayoutInflater()) ;
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_holder,new HomeFragment())
                .commit();
        bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottom_navigation);
        iBottomNavigationHelper = new BottomNavigationHelper( this, bottomNavigationView );
        iBottomNavigationHelper.setOnBottomNavigationViewItemClick();

    }
        
}