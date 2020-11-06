package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.databinding.ActivityHomeBinding;
import com.example.pc.bagsshoes.databinding.ActivityProductBinding;

@AndroidEntryPoint
public class ProductActivity extends AppCompatActivity {

    private ActivityProductBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityProductBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.product_fragment_holder,new ProductFragment())
                .commit();
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = (com.google.android.material.bottomnavigation.BottomNavigationView) binding.productBottomNavigation;


        bottomNavigationView.setOnNavigationItemSelectedListener(new com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Toast.makeText(ProductActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_buy:
                        Toast.makeText(ProductActivity.this, "Buy", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemCart = menu.findItem(R.id.action_add_to_cart );
        if(itemCart != null){

        }

        return super.onPrepareOptionsMenu( menu );
    }
}