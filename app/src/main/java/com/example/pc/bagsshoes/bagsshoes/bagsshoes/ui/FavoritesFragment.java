package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters.ProductAdapter;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductDetailViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.ProductViewModel;
import com.example.pc.bagsshoes.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class FavoritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentFavoritesBinding binding;
    private ProductDetailViewModel productDetailViewModel;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        binding = FragmentFavoritesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        productDetailViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        initRecyclerView();
        observeData();
        productDetailViewModel.getFavoriteProducts();
    }

    private void observeData() {
        Log.i("observeData","enter");
        productDetailViewModel.getFavoriteProductList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                Log.e(TAG, "onChanged: " + products.size() );

                if(products == null || products.size() == 0)

                    binding.noFavoritesText.setVisibility(View.VISIBLE);
                else{
                    adapter.updateList(products);

                }
            }
        });
    }

    private void initRecyclerView() {
        binding.favoritesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoritesRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ProductAdapter( getContext(), productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        } );
        binding.favoritesRecyclerview.setAdapter(adapter);
    }
}