package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters.CartAdapter;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters.ProductAdapter;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.CartViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductDetailViewModel;
import com.example.pc.bagsshoes.databinding.FragmentCartBinding;
import com.example.pc.bagsshoes.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentCartBinding binding;
    private CartAdapter adapter;
    private ArrayList<Product> cartList;
    private CartViewModel cartViewModel;
    private CartAdapter.OnClickListener onClickListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        initRecyclerView();
        cartViewModel = new ViewModelProvider(this).get( CartViewModel.class);
        cartViewModel.getCartProducts( new SharedPreferencesHelper( getContext() ).getUserId() );
        observeData();
        observeRemovingtoCart();

        binding.checkoutBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CheckoutActivity.class);
                i.putExtra( "amount", binding.totalPrice.getText().toString() );
                ArrayList<Integer> idsList = new ArrayList();

                //Retrieving products ids for the checkout to save them on server for accountability
                for(int j = 0; j< adapter.getList().size(); ++j)
                    idsList.add(adapter.getList().get(j).getId());

                i.putIntegerArrayListExtra( "products",  idsList);
                startActivity( i );

            }
        } );

    }

    private void initRecyclerView() {
        cartList = new ArrayList<>();
        binding.cartRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new CartAdapter( getContext(), cartList, new CartAdapter.OnClickListener() {
            @Override
            public void onCloseClick(View view, int position) {
                cartViewModel.removeProductFromCart(  new SharedPreferencesHelper( getContext() ).getUserId(), adapter.getProductAt( position ).getId()  );
                updateListAfterRemoval(position);
            }
        } );
        binding.cartRecyclerview.setAdapter(adapter);

    }

    private void observeData() {

        cartViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                Log.e(TAG, "onChanged: " + products.size() );

                if(products == null || products.size() == 0)

                    binding.noFavoritesText.setVisibility(View.VISIBLE);
                else{
                    adapter.updateList(products);
                    binding.totalPrice.setText( "" + adapter.getTotalPrice() );

                }
            }
        });
        binding.totalPrice.setText( "" + adapter.getTotalPrice() + "$" );
    }


    private void observeRemovingtoCart(){
        cartViewModel.getProductActionResponse().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && !s.isEmpty()){
                    adapter.notifyDataSetChanged();
                    Toast.makeText( getContext(),  s, Toast.LENGTH_SHORT ).show();
                    binding.totalPrice.setText( "" + adapter.getTotalPrice() + "$" );


                }

            }
        } );
    }

    private void updateListAfterRemoval(int position){
        adapter.getList().remove( position );
        adapter.notifyDataSetChanged();
    }
}