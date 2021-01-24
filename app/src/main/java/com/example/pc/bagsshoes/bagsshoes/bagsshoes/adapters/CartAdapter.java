package com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.ProductActivity;
import com.example.pc.bagsshoes.databinding.RecyclerviewCartItemLayoutBinding;
import com.example.pc.bagsshoes.databinding.RecyclerviewItemLayoutBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Context mContext;
    private ArrayList<Product> mList;
    private RecyclerviewCartItemLayoutBinding binding;
    private CartAdapter.OnClickListener onClickListener;

    public CartAdapter(Context mContext, ArrayList<Product> mList, CartAdapter.OnClickListener onClickListener){
        this.mContext = mContext;
        this.mList = mList;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        public void onCloseClick(View view, int position);
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = RecyclerviewCartItemLayoutBinding.inflate(inflater,parent,false);
        CartAdapter.CartViewHolder cartViewHolder= new CartAdapter.CartViewHolder(binding);

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.itemLayoutBinding.productBrand.setText(mList.get(position).getBrand());
        holder.itemLayoutBinding.productPrice.setText("" + mList.get(position).getPrice() + "$");
        Glide.with(mContext).load( R.drawable.ic_action_delete)
                .into(holder.itemLayoutBinding.closeImage);

        Glide.with(mContext).load( StringRProvider.BASE_URL + mList.get(position).getImgUrl())
                .into(holder.itemLayoutBinding.productImage);

        holder.itemLayoutBinding.closeImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onCloseClick(v, position);
            }
        } );


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0: mList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        private RecyclerviewCartItemLayoutBinding itemLayoutBinding;
        public CartViewHolder(RecyclerviewCartItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;

        }

    }

    public  void updateList(ArrayList<Product> updatedList){
        mList = updatedList;
        notifyDataSetChanged();

    }

    public ArrayList<Product> getList(){
        return this.mList;
    }

    public  Product getProductAt(int position){
        return mList.get(position);
    }

    public double getTotalPrice(){
        double sum = 0;
        for(int i=0;  i< mList.size(); i++){
            sum= sum + mList.get( i ).getPrice();
        }
        return sum;
    }
}
