package com.example.yoant.foodcritic.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.GroupFragmentAdapter;
import com.example.yoant.foodcritic.core.FoodGroup;

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
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initialiseDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vitamins, container, false);
        rootView.setTag(TAG);

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.products_recycler_view_fragment);
        mAdapter = new GroupFragmentAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void initialiseDataSet(){
        mDataSet = FoodGroup.items;
    }

}
