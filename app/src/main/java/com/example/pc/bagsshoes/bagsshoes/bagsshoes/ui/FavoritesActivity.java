package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.databinding.ActivityFavoritesBinding;

@AndroidEntryPoint
public class FavoritesActivity extends AppCompatActivity {

    private ActivityFavoritesBinding binding;
    private FavoritesFragment favoritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityFavoritesBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.favorites_fragment_holder,new FavoritesFragment())
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
                        Toast.makeText(FavoritesActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(FavoritesActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}