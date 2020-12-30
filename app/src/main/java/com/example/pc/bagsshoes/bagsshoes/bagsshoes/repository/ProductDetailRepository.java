package com.example.pc.bagsshoes.bagsshoes.bagsshoes.repository;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.db.ProductDao;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ProductDetailRepository {
    private ProductDao productDao;

    @Inject
    public ProductDetailRepository(ProductDao productDao){
        this.productDao = productDao;
    }
    public Observable<List<Product>> getFavoriteProducts(){
        return productDao.getAllFavoriteProducts();
    }

    public void deleteProductFromFavorites(long id){
        productDao.deleteProductFromFavorites( id );
    }

    public void addProductToFavorites(Product product){
        productDao.addProductToFavorites( product );
    }

    public Observable<Boolean> isFavorite(long id){
        return productDao.isFavorite( id );
    }

}
