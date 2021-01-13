package com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel;

import android.util.Log;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SchedulerProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.UserRegistrationRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class UserRegistrationViewModel extends ViewModel {
    private UserRegistrationRepository userRegistrationRepository;
    private MutableLiveData<AuthenticationResponse> response = new MutableLiveData<>();

    @ViewModelInject
    public UserRegistrationViewModel(UserRegistrationRepository userRegistrationRepository){
        this.userRegistrationRepository = userRegistrationRepository;
    }

    public MutableLiveData<AuthenticationResponse> getAuthenticationResponse(){
        return response;
    }

    public void registerUser(User user){
        userRegistrationRepository.registerUser( user )
                .subscribeOn( SchedulerProvider.getInstance().io() )
                .observeOn( SchedulerProvider.getInstance().ui() )
                .subscribe( new Observer<AuthenticationResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AuthenticationResponse authenticationResponse) {
                        response.setValue( authenticationResponse );

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e( "ERREUR", "is " + e );
                        /*
                          Handling different cases: user may already exist, email not provided or malformed,
                        * password not provided, email malformed.
                        *
                        * */
                        if (e instanceof HttpException) {
                            ResponseBody body = (( HttpException ) e).response().errorBody();

                            try {
                                String error = body.string();
                                JSONObject json = new JSONObject(error);
                                AuthenticationResponse authenticationResponse = new AuthenticationResponse("", json.getString( "error" ));
                                response.setValue(authenticationResponse);
                                Log.i( "Erno", "ERROR: " + error );


                            } catch (IOException | JSONException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    public String getToken(){
        return this.userRegistrationRepository.getToken();
    }

    public void setToken(String token){
        this.userRegistrationRepository.setToken( token );
    }

    public boolean isLogin(){
        return this.userRegistrationRepository.isLogin();
    }

    public void setLogin(boolean login){
        this.userRegistrationRepository.setLogin( login );
    }
}
