package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;


import android.os.Bundle;


import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.databinding.ActivityLoginBinding;


import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityLoginBinding.inflate( getLayoutInflater()) ;
        setContentView( binding.getRoot() );
        getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_holder,new LoginFragment())
                .commit();
    }


}
