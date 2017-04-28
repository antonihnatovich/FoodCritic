package com.example.yoant.foodcritic.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.curentlyused.GroupFragmentAdapter;
import com.example.yoant.foodcritic.adapters.curentlyused.RecyclerViewItemClickListener;
import com.example.yoant.foodcritic.models.FoodGroup;
import com.example.yoant.foodcritic.helper.ItemDecorationRecyclerViewColumns;
import com.example.yoant.foodcritic.view.activities.ProductsActivity;
import com.example.yoant.foodcritic.view.activities.ProductsTabActivity;
import com.example.yoant.foodcritic.view.activities.tab_experiment.TestActivity;

public class VitaminsGroupFragment extends Fragment {

    private static final String TAG = "VitaminsGroupFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    protected RecyclerView mRecyclerView;
    protected GroupFragmentAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected FoodGroup[] mDataSet;


    public VitaminsGroupFragment() {
        //setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initialiseDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vitamins_group, container, false);
        rootView.setTag(TAG);

        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = new Intent(this.getActivity(), ProductsActivity.class);
        final Intent test = new Intent(this.getActivity(), ProductsTabActivity.class);
        final Intent test1 = new Intent(this.getActivity(), TestActivity.class);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        setRecyclerViewLayoutManager(test1);
        mAdapter = new GroupFragmentAdapter(mDataSet, getContext());
        mRecyclerView.setAdapter(mAdapter);



        return rootView;
    }

    public void setRecyclerViewLayoutManager(final Intent intent){

        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null)
            scrollPosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findFirstVisibleItemPosition();

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.addItemDecoration(new ItemDecorationRecyclerViewColumns(
                getResources().getDimensionPixelSize(R.dimen.vitaminsGroupItemSpacing),
                2
        ));

        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), mRecyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getContext(), "Hello from long " + position, Toast.LENGTH_SHORT).show();
            }
        }));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    private void initialiseDataSet(){
        mDataSet = FoodGroup.items;
    }

}
