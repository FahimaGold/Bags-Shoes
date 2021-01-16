package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.AuthenticationResponse;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.User;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.UserCredentials;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.UserAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.AuthenticationRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductDetailRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class AuthenticationRepositoryTest {
    @Mock
    private UserAPIService userAPIService;
    @Mock
    private SharedPreferencesHelper sharedPreferencesHelper;

    private AuthenticationRepository authenticationRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );
        authenticationRepository = new AuthenticationRepository( userAPIService, sharedPreferencesHelper );
    }

    @Test
    public void registerUserTest(){
        User user = new User("some.email@example.com", "firtname", "surname", "0770369971", "somePassword");
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("gggg99aaddfxz", "password");
        when(userAPIService.registerUser( user )).thenReturn( Observable.just( authenticationResponse ) );
        TestObserver<AuthenticationResponse> testObserver = new TestObserver<>();
        authenticationRepository.registerUser( user ).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValue(authenticationResponse);
    }

    @Test
    public void loginUserTest(){
        UserCredentials user = new UserCredentials("some.email@example.com",  "somePassword");
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("gggg99aaddfxz", "password");
        when(userAPIService.loginUser( user )).thenReturn( Observable.just( authenticationResponse ) );
        TestObserver<AuthenticationResponse> testObserver = new TestObserver<>();
        authenticationRepository.loginUser( user ).subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValue(authenticationResponse);
    }

    @Test
    public void getAndSetTokenTest(){
        String token = "AAA12CSDZ.ADDSZZ.MMLL9";

        //Testing getToken()
        when(sharedPreferencesHelper.getToken()).thenReturn( token );
        Assert.assertEquals( token, authenticationRepository.getToken() );

        //Testing setToken()
        authenticationRepository.setToken( token );
        Assert.assertEquals( token, authenticationRepository.getToken());
    }

    @Test
    public void isAndSetLoginTest(){
        boolean login = true;

        //Testing isLogin()
        when(sharedPreferencesHelper.isLogin()).thenReturn( login );
        Assert.assertEquals( login, authenticationRepository.isLogin() );

        //Testing setLogin
        authenticationRepository.setLogin( login );
        Assert.assertEquals( login, authenticationRepository.isLogin() );
    }

}
