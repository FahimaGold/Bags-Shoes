package com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.ProductActivity;
import com.example.pc.bagsshoes.databinding.RecyclerviewItemLayoutBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context mContext;
    private ArrayList<Product> mList;
    private RecyclerviewItemLayoutBinding binding;


    public CartAdapter(Context mContext, ArrayList<Product> mList){
        this.mContext = mContext;
        this.mList = mList;

    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = RecyclerviewItemLayoutBinding.inflate(inflater,parent,false);
        CartAdapter.CartViewHolder cartViewHolder= new CartAdapter.CartViewHolder(binding);

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.itemLayoutBinding.productBrand.setText(mList.get(position).getBrand());
        holder.itemLayoutBinding.productPrice.setText("" + mList.get(position).getPrice() + "$");
        Toast.makeText( mContext, "inside cart " + mList.get(position).getPrice() , Toast.LENGTH_SHORT ).show();
        Glide.with(mContext).load( StringRProvider.BASE_URL + mList.get(position).getImgUrl())
                .into(holder.itemLayoutBinding.productImage);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0: mList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        private RecyclerviewItemLayoutBinding itemLayoutBinding;
        public CartViewHolder(RecyclerviewItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;

        }

    }

    public  void updateList(ArrayList<Product> updatedList){
        mList = updatedList;
        notifyDataSetChanged();

    }

    public  Product getProductAt(int position){
        return mList.get(position);
    }
}
