package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;


import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ProductRepositoryTest {

    @Mock
    private ProductAPIService productAPIService;
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );
        productRepository = new ProductRepository( productAPIService );
    }

    @Test
    public void getProductsTest(){
        List<Product> products = new ArrayList<>();
        Product p1 = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        Product p2 = new Product( 2, "Aldo", 4236, "", "Aldo shoe", "SHOE" );
        products.add( p1 );
        products.add( p2 );
        when(productAPIService.getProducts()).thenReturn( Observable.just( products ) );
        TestObserver<List<Product>> testObserver = new TestObserver ();
        productRepository.getProducts().subscribe( testObserver );
        testObserver.assertNoErrors();
        testObserver.assertValue(products);

    }

}

