package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.SharedPreferencesHelper;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Cart;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.StringRProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.CartViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductDetailViewModel;
import com.example.pc.bagsshoes.databinding.FragmentProductBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentProductBinding binding;
    private ProductDetailViewModel productDetailViewModel;
    private CartViewModel cartViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        binding = FragmentProductBinding.inflate( inflater );
        binding.favoritesIcon.setColorFilter( ContextCompat.getColor( getContext(),R.color.colorPrimary ), PorterDuff.Mode.DST_IN );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        productDetailViewModel = new ViewModelProvider(this).get( ProductDetailViewModel.class);
        Intent i = getActivity().getIntent();
        Product p = i.getParcelableExtra( "Product" );
        binding.productDetailBrand.setText( p.getBrand() );
        Glide.with(this).load( StringRProvider.BASE_URL + p.getImgUrl())
                .into(binding.productDetailImage);
        binding.productDetailPrice.setText( p.getPrice() + "$");
        binding.descriptionContent.setText( p.getDescription() );
        binding.productCode.setText( p.getId() + "" );
        binding.available.setText( "In store" );
        observeData();
        productDetailViewModel.isFavorite( p.getId() );

        //Changing the "heart" favorites icon on click
        binding.favoritesIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    productDetailViewModel.addProductToFavorites( p );
                    Toast.makeText( getContext(), "" + p.getBrand() + " added to favorites!", Toast.LENGTH_SHORT ).show();
                    binding.favoritesIcon.setColorFilter( ContextCompat.getColor( getContext(),R.color.colorPrimary ), PorterDuff.Mode.SRC_IN);


            }
        } );

        cartViewModel = new ViewModelProvider(this).get( CartViewModel.class);
        observeAddingtoCart();
        ProductActivity.activityProductBinding.productBottomNavigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        int productId = Integer.parseInt(binding.productCode.getText().toString());
                        cartViewModel.addProductToCart( new Cart( new SharedPreferencesHelper( getContext()).getUserId(), productId) );
                        break;
                    case R.id.action_buy:
                        Toast.makeText(getContext(), "Buy", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        } );
    }


    private void observeData() {

        productDetailViewModel.checkFavorite().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    binding.favoritesIcon.setColorFilter( ContextCompat.getColor( getContext(),R.color.colorPrimary ), PorterDuff.Mode.SRC_IN);

            }

        });
    }

    private void observeAddingtoCart(){
        cartViewModel.getProductActionResponse().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && !s.isEmpty())
                    Toast.makeText( getContext(),  s, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}