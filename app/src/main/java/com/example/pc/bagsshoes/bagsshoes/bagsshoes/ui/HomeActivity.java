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
import com.example.pc.bagsshoes.databinding.ActivityHomeBinding;


@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {


    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityHomeBinding.inflate( getLayoutInflater()) ;
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_holder,new HomeFragment())
                .commit();



        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity( i );
                        break;
                    case R.id.action_favorite:
                         i = new Intent(getApplicationContext(), FavoritesActivity.class);
                        startActivity( i );
                        break;
                    case R.id.action_cart:
                        Toast.makeText(HomeActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), CartActivity.class );
                        startActivity( i );
                        break;
                    case R.id.action_profile:
                        Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
        
}