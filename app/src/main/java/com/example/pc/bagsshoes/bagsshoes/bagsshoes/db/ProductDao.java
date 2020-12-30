package com.example.pc.bagsshoes.bagsshoes.bagsshoes.db;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.google.android.material.circularreveal.CircularRevealHelper;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Observable;


@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProductToFavorites(Product product);

    @Query( "DELETE FROM product WHERE id = :id;" )
    void deleteProductFromFavorites(long id);

    @Query( "SELECT * FROM product;" )
    Observable<List<Product>> getAllFavoriteProducts();

    @Query( "SELECT EXISTS (SELECT 1 FROM product where id = :id)" )
    Observable<Boolean> isFavorite(long id);
}
