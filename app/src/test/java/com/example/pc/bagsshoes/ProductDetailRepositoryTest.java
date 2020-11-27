package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.ProductDao;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductDetailRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ProductDetailRepositoryTest {

    @Mock
    private ProductDao productDao;
    private ProductDetailRepository productDetailRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );
        productDetailRepository = new ProductDetailRepository( productDao );
    }


    @Test
    public void getFavoriteProductsTest(){
        List<Product> products = new ArrayList<>();
        Product p1 = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        Product p2 = new Product( 2, "Aldo", 4236, "", "Aldo shoe", "SHOE" );
        products.add( p1 );
        products.add( p2 );
        when(productDao.getAllFavoriteProducts()).thenReturn( Observable.just( products ) );
        TestObserver<List<Product>> testObserver = new TestObserver ();
        productDetailRepository.getFavoriteProducts().subscribe( testObserver );
        testObserver.assertNoErrors();
        testObserver.assertValue(products);
    }

    @Test
    public void addProductToFavoritesTest(){
        Product p = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        //Act
        productDetailRepository.addProductToFavorites( p );
        //Assert
        verify( productDao ).addProductToFavorites( p );
        verifyNoMoreInteractions( productDao );
    }
}
