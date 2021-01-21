package com.example.pc.bagsshoes.bagsshoes.bagsshoes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product implements Parcelable {
    @PrimaryKey
    private int id;
    private String brand;
    private int price;
    private String imgUrl;
    private String description;
    private String category;

    @Ignore
    public Product (){

    }


     public Product(int id, String brand,int price, String imgUrl, String description, String category){
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.category = category;
    }

    public Product(Parcel in) {
        id = in.readInt();
        brand = in.readString();
        price = in.readInt();
        imgUrl = in.readString();
        description = in.readString();
    }


    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product( in );
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( id );
        dest.writeString( brand );
        dest.writeInt( price );
        dest.writeString( imgUrl );
        dest.writeString( description );
        dest.writeString( category );
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == this)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        Product p2 = (Product) obj;
        return id == p2.getId()
                && price == p2.getPrice()
                && (brand == p2.getBrand() || (brand != null) && brand.equals( p2.brand ))
                && (description == p2.getDescription() || (description != null && description.equals( p2.getDescription() )))
                && (category == p2.getCategory() || (category != null && category.equals( p2.getCategory() )))
                && (imgUrl == p2.getImgUrl() || (imgUrl != null && imgUrl.equals( p2.getImgUrl() )));
    }
}
