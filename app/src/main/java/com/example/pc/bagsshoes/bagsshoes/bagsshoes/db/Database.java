package com.example.pc.bagsshoes.bagsshoes.bagsshoes.db;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import androidx.room.RoomDatabase;

@androidx.room.Database( entities = {Product.class}, version=1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract  ProductDao productDao();
}
