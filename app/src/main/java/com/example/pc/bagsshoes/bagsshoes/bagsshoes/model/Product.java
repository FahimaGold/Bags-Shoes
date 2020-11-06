package com.example.pc.bagsshoes.bagsshoes.bagsshoes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product implements Parcelable {
    @PrimaryKey
    private long id;
    private String brand;
    private int price;
    private String imgUrl;
    private String description;
    private String category;
/*    private int id;
    private String title;
    private boolean completed;*/
    @Ignore
    public Product (){

    }


     public Product(long id, String brand,int price, String imgUrl, String description, String category){
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.category = category;
    }

    public Product(Parcel in) {
        id = in.readLong();
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

    public long getId() {
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

    public void setId(long id) {
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
        dest.writeLong( id );
        dest.writeString( brand );
        dest.writeInt( price );
        dest.writeString( imgUrl );
        dest.writeString( description );
        dest.writeString( category );
    }

}
