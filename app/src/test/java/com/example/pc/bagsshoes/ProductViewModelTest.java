package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.network.ProductAPIService;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subscribers.TestSubscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class ProductViewModelTest {

    /*Swaps the background executor with a different one that executes each task synchronously
    so that assertEquals doesn't get called before the the other methods if they run async
    Hence avoid any problem with the test result.
    This rule is important for testing LiveData.
    */
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    /*To override the default schedulers;  using another scheduler instead of an  android one as these are unit tests,
     independent from android (for example, AndroidSchedulers.mainThread() is an instance of LooperScheduler
     and relies on Android dependencies that are not available in JUnit tests.)
     */
    @Rule public TestSchedulerRule testSchedulerRule = new TestSchedulerRule();

  @Mock
   private ProductRepository productRepository;
   private ProductViewModel productViewModel;

  @Before
    public void setUp() {
       MockitoAnnotations.initMocks(this);
       productViewModel = new ProductViewModel( productRepository );

  }

    @Test
    public void getProductsTest() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        Product p2 = new Product( 2, "Aldo", 4236, "", "Aldo shoe", "SHOE" );
        products.add( p1 );
        products.add( p2 );
        when(productRepository.getProducts()).thenReturn( Observable.just( products ) );
        productViewModel.getProducts();

        //Making sure the MutableLIveData was updated by productViewModel.getProducts
        Assert.assertEquals( products,productViewModel.getProductList().getValue());
    }


}
