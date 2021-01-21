package com.example.pc.bagsshoes;

import android.util.Log;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.UserCredentials;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.AuthenticationRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.AuthenticationViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationViewModelTest {
    //Replacing the background task executor with one that runs everything synchronously
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    /*To override the default schedulers;  using another scheduler instead of an  android one as these are unit tests,
     independent from android (for example, AndroidSchedulers.mainThread() is an instance of LooperScheduler
     and relies on Android dependencies that are not available in JUnit tests.)
     */
    @Rule public TestSchedulerRule testSchedulerRule = new TestSchedulerRule();

    @Mock
    private AuthenticationRepository authenticationRepository;
    private AuthenticationViewModel authenticationViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationViewModel = new AuthenticationViewModel( authenticationRepository );

    }

    @Test
    public void registerUserTest(){
        User user = new User("some.email@example.com", "firtname", "surname", "0770369971", "somePassword");
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(1,"gggg99aaddfxz", "password");
        when( authenticationRepository.registerUser( user ) ).thenReturn( Observable.just( authenticationResponse ) );
        authenticationViewModel.registerUser( user );
        Assert.assertEquals( authenticationResponse,authenticationViewModel.getAuthenticationResponse().getValue());

    }

    @Test
    public void loginUserTest(){
        UserCredentials user = new UserCredentials("some.email@example.com","somePassword");
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(1,"gggg99aaddfxz", "password");
        when( authenticationRepository.loginUser( user ) ).thenReturn( Observable.just( authenticationResponse ) );
        authenticationViewModel.loginUser( user );
        Assert.assertEquals( authenticationResponse,authenticationViewModel.getAuthenticationResponse().getValue());


    }

    @Test
    public void getAndSetTokenTest(){
        String token = "AAZZ1XXX.QQQQ.AAAA7";
        //Testing getToken()
        when(authenticationRepository.getToken()).thenReturn( token );
        Assert.assertEquals(token, authenticationRepository.getToken());

        //Testing setToken()
        authenticationViewModel.setToken( token );
        Assert.assertEquals( token, authenticationViewModel.getToken() );
    }

    @Test
    public void isAndSetloginTest(){
        boolean login = true;
        //Testing isLogin
        when(authenticationRepository.isLogin()).thenReturn( login );
        Assert.assertEquals( login, authenticationViewModel.isLogin() );

        //Testing setLogin
        authenticationViewModel.setLogin( login );
        Assert.assertEquals( login, authenticationViewModel.isLogin() );
    }

}
