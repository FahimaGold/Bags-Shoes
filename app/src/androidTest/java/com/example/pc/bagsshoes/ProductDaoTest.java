package com.example.pc.bagsshoes;

import android.content.Context;


import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.Database;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.ProductDao;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ProductDaoTest {

    //Replacing the background task executor with one that runs everything synchronously
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
      private Database db;
      private ProductDao productDao;
    @Before
    public void createDatabase(){

        Context context = ApplicationProvider.getApplicationContext();

        //Creating the test database in the memory so that it will only exists during the test process
        db = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        productDao = db.productDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

   @Test
    public void addAndGetFavoritesAndDeleteTest() throws Exception {
        Product p = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        List<Product> products = new ArrayList<>();
        products.add( p );
        productDao.addProductToFavorites( p);
        TestObserver<List<Product>> testObserver = new TestObserver<>();
        productDao.getAllFavoriteProducts().subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValue( products );
        products.remove( p );
        productDao.deleteProductFromFavorites( 1 );
        testObserver = new TestObserver<>();
        productDao.getAllFavoriteProducts().subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertEmpty();

    }


}
