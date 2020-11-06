package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.os.Bundle;
import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.databinding.ActivityAuthenticationBinding;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthenticationActivity extends AppCompatActivity {
    private ActivityAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityAuthenticationBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace( R.id.signup_fragment_holder,new SignupFragment())
                .commit();
    }
}