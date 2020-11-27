package com.example.pc.bagsshoes;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository.ProductDetailRepository;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductDetailViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ProductDetailViewModelTest {

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
    private ProductDetailRepository productDetailRepository;
    private ProductDetailViewModel productDetailViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productDetailViewModel = new ProductDetailViewModel( productDetailRepository );

    }

    @Test
    public void getFavoriteProductsTest() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        Product p2 = new Product( 2, "Aldo", 4236, "", "Aldo shoe", "SHOE" );
        products.add( p1 );
        products.add( p2 );
        when(productDetailRepository.getFavoriteProducts()).thenReturn( Observable.just( products ) );
        productDetailViewModel.getFavoriteProducts();

        Assert.assertEquals( products,productDetailViewModel.getFavoriteProductList().getValue());
    }

    @Test
    public void addProductToFavoritesTest() {
        Product p = new Product( 1, "Gucci", 3642, "", "Gucci Shoe", "SHOE" );
        productDetailViewModel.addProductToFavorites( p );
        verify( productDetailRepository ).addProductToFavorites( p );
        verifyNoMoreInteractions( productDetailRepository );

    }

}
