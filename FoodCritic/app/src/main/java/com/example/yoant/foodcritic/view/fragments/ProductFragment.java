package com.example.yoant.foodcritic.view.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.Product;

public class ProductFragment extends android.app.Fragment {
    private Product[] mDataSet;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;


    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);

//        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.scrollToPosition(0);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_products);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setAdapter(new ProductAdapter(mDataSet, getContext())));


        return inflater.inflate(R.layout.fragment_product, container, false);


    }

    private void initDataSet() {
        mDataSet = Product.products;
    }
}
