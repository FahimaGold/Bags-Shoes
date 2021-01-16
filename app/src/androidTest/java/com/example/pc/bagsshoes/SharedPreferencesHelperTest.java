package com.example.pc.bagsshoes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class SharedPreferencesHelperTest {

    //Replacing the background task executor with one that runs everything synchronously
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Mock
    private SharedPreferences prefs;
    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        prefs = context.getSharedPreferences( "prefs", Context.MODE_PRIVATE );
        sharedPreferencesHelper = new SharedPreferencesHelper( context);
    }

    @Test
    public void getAndSetTokenTest(){


    }
}
