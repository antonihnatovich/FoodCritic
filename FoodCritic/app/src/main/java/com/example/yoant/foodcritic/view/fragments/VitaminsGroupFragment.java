package com.example.yoant.foodcritic.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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
import com.example.yoant.foodcritic.adapters.GroupFragmentAdapter;
import com.example.yoant.foodcritic.adapters.RecyclerViewItemClickListener;
import com.example.yoant.foodcritic.core.FoodGroup;
import com.example.yoant.foodcritic.helper.ItemDecorationRecyclerViewColumns;
import com.example.yoant.foodcritic.view.activities.MainActivity;

public class VitaminsGroupFragment extends Fragment {

    private static final String TAG = "VitaminsGroupFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    protected RecyclerView mRecyclerView;
    protected GroupFragmentAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected FoodGroup[] mDataSet;
    protected LayoutManagerType mCurrentLayoutManagerType;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

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
        //ActionBar toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        ((AppCompatActivity)getActivity()).getSupportActionBar();
//        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        setRecyclerViewLayoutManager();
        mAdapter = new GroupFragmentAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setRecyclerViewLayoutManager(){
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
                Toast.makeText(getContext(), "Item clicled at " + position + " position", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
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
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
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
