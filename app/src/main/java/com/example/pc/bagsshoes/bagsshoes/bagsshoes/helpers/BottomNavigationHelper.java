package com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.CartActivity;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.FavoritesActivity;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

public class BottomNavigationHelper implements IBottomNavigationHelper{

    private BottomNavigationView bottomNavigationView;
    private Context context;
    public BottomNavigationHelper(Context context, BottomNavigationView bottomNavigationView){
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;

    }

    @Override
    public void setOnBottomNavigationViewItemClick() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent i = new Intent(context, HomeActivity.class);
                        context.startActivity( i );
                        break;
                    case R.id.action_favorite:
                        i = new Intent(context, FavoritesActivity.class);
                        context.startActivity( i );
                        break;
                    case R.id.action_cart:
                        i = new Intent(context, CartActivity.class );
                        context.startActivity( i );
                        break;
                    case R.id.action_profile:
                        Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
