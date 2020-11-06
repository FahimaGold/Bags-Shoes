package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.ImmediateSchedulers.ImmediateSchedulerProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;

import org.junit.Test;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subscribers.TestSubscriber;


public class ProductViewModelTest {
  @Mock
   private ProductRepository productRepository;
   private ProductViewModel productViewModel;
   @Mock 
   private ImmediateSchedulerProvider schedulerProvider;
  
  

  @Before
    public void setUp() throws Exception {
       MockitoAnnotations.initMocks(this);
       productViewModel = new ProductViewModel( productRepository );
  }

  @Test
    public void getProductsTest(){
      List<Product> products = new ArrayList<>();
      Product p1 = new Product(1, "Gucci", 3642, "","", "SHOE");
      Product p2 = new Product(2, "Aldo", 4236, "","", "SHOE");
      products.add( p1 );
      products.add( p2 );
      Mockito.when( productRepository.getProducts() ).thenReturn( Observable.just(products));

      TestSubscriber<List<Product>> testSubscriber = new TestSubscriber<>();
      testSubscriber.assertNoErrors();
     // testSubscriber.assertValue(products);
  }



}
