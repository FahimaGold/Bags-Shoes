package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.UserCredentials;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.AuthenticationViewModel;
import com.example.pc.bagsshoes.databinding.FragmentLoginBinding;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

/**

 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentLoginBinding binding;
    private AuthenticationViewModel authenticationViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        authenticationViewModel = new ViewModelProvider( this ).get( AuthenticationViewModel.class );
        //Checking if user is already logged in, if so, they'll be redirected to HomeActivity right the way.
        if(authenticationViewModel.isLogin()){
            Intent i = new Intent(getContext(), HomeActivity.class);
            startActivity( i );
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );


        observeData();
        binding.signupText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SignupActivity.class);
                startActivity( i );
            }
        } );
        binding.loginbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserCredentials userCredentials = new UserCredentials( binding.email.getText().toString().trim(), binding.password.getText().toString().trim() );
                authenticationViewModel.loginUser( userCredentials );

            }
        } );
    }

    private void  observeData(){
        authenticationViewModel.getAuthenticationResponse().observe(getViewLifecycleOwner(), new Observer<AuthenticationResponse>() {
            @Override
            public void onChanged(AuthenticationResponse response) {

                if(!response.getToken().equals( "" ) ){
                    //Registration successful,store token
                    authenticationViewModel.setToken( response.getToken() );
                    authenticationViewModel.setLogin( true );
                    Intent i = new Intent(getContext(), HomeActivity.class);
                    startActivity( i );
                }


                //Token not received due to a registration error
                // Log.i("authResp","message " + response.getError());
                //Password not provided
                else if((response.getError().equals( "Password is required" ))){
                    binding.password.setError( "Password is required" );
                    binding.password.requestFocus();
                }
                //Wrong password
                else if((response.getError().equals( "Wrong password" ))){
                    binding.password.setError( "Wrong password" );
                    binding.password.requestFocus();
                }
                //Email not provided or malformed
                else if(response.getError().equals( "Email is required or malformed" )){
                    binding.email.setError( "Email is required or malformed" );
                    binding.email.requestFocus();
                }
                //User doesn't exist
                else if(response.getError().equals( "User does not exist")){
                    binding.email.setError( "This user does not exist" );
                    binding.email.requestFocus();
                }


            }
        });

    }
}