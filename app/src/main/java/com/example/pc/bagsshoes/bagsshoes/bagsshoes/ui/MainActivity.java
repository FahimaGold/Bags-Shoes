package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;


import android.os.Bundle;


import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityMainBinding.inflate( getLayoutInflater()) ;
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_holder,new MainFragment())
                .commit();
    }


}
