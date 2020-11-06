package com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.providers.StringRProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui.ProductActivity;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;
import com.example.pc.bagsshoes.databinding.RecyclerviewItemLayoutBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<Product> mList;
    private RecyclerviewItemLayoutBinding binding;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public ProductAdapter(Context mContext, ArrayList<Product> mList , OnItemClickListener mOnItemClickListener){
        this.mContext = mContext;
        this.mList = mList;
        this.mOnItemClickListener = mOnItemClickListener;

    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = RecyclerviewItemLayoutBinding.inflate(inflater,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(binding);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.itemLayoutBinding.productBrand.setText(mList.get(position).getBrand());
        holder.itemLayoutBinding.productPrice.setText("" + mList.get(position).getPrice() + "DA");

        Glide.with(mContext).load( StringRProvider.BASE_URL + mList.get(position).getImgUrl())
                .into(holder.itemLayoutBinding.productImage);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition());
                Toast.makeText( mContext, "CLicked:", Toast.LENGTH_SHORT ).show();
                Intent i = new Intent(mContext, ProductActivity.class );
                Product p = new Product(mList.get( position ).getId(), mList.get( position ).getBrand(), mList.get( position ).getPrice(), mList.get( position ).getImgUrl(), mList.get( position ).getDescription(), mList.get( position ).getCategory());

                i.putExtra( "Product", p);
                mContext.startActivity( i );

            }
        } );


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0: mList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private RecyclerviewItemLayoutBinding itemLayoutBinding;
        public ProductViewHolder(RecyclerviewItemLayoutBinding itemLayoutBinding) {
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
