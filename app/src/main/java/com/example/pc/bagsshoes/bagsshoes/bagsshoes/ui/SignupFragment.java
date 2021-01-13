package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.UserRegistrationViewModel;
import com.example.pc.bagsshoes.databinding.FragmentSignupBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import static android.content.ContentValues.TAG;

/**

 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentSignupBinding binding;
    private UserRegistrationViewModel userRegistrationViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuthenticationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate( inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );


        userRegistrationViewModel = new ViewModelProvider( this ).get( UserRegistrationViewModel.class );
        observeData();


        binding.signupbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(binding.email.getText().toString(), binding.firstName.getText().toString(), binding.surname.getText().toString(), binding.phone.getText().toString(), binding.password.getText().toString());
                userRegistrationViewModel.registerUser( user );
                //Intent i = new Intent(getContext(), HomeActivity.class);
                //startActivity( i );
            }
        } );
    }

    private void observeData(){
        userRegistrationViewModel.getAuthenticationResponse().observe(getViewLifecycleOwner(), new Observer<AuthenticationResponse>() {
            @Override
            public void onChanged(AuthenticationResponse response) {

                if(!response.getToken().equals( "" ) ){
                    //Registration successful,store token
                    userRegistrationViewModel.setToken( response.getToken() );
                    userRegistrationViewModel.setLogin( true );
                    Intent i = new Intent(getContext(), HomeActivity.class);
                    startActivity( i );
                }


                    //Token not received due to a registration error
                   // Log.i("authResp","message " + response.getError());
                    //Password not pprovided
                else if((response.getError().equals( "Password is required" ))){
                        binding.password.setError( "Password is required" );
                        binding.password.requestFocus();
                    }
                    //Email not provided or malformed
                else if(response.getError().equals( "Email is required or malformed" )){
                        binding.email.setError( "Email is required or malformed" );
                        binding.email.requestFocus();
                    }
                    //User already exist
                else if(response.getError().equals( "User may already exist")){
                        binding.email.setError( "User may already exist! Please try again with another email" );
                        binding.email.requestFocus();
                    }



            }
        });
    }
}