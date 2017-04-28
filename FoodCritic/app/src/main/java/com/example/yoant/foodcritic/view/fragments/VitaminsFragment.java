package com.example.yoant.foodcritic.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.curentlyused.VitaminsRecyclerViewAdapter;
import com.example.yoant.foodcritic.models.FoodGroup;


public class VitaminsFragment extends Fragment {

    private static final String TAG = "VitaminsFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    protected VitaminsRecyclerViewAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected FoodGroup[] mDataset;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }


    public VitaminsFragment() {
    }

//    public static VitaminsFragment newInstance(int columnCount) {
//        VitaminsFragment fragment = new VitaminsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vitamins, container, false);
        rootView.setTag(TAG);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_vitamins);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
        mAdapter = new VitaminsRecyclerViewAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
//        super.onSaveInstanceState(savedInstanceState);
//    }

    public void setRecyclerViewLayoutManager(LayoutManagerType type){
        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null)
            scrollPosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findFirstVisibleItemPosition();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    private void initDataset() {
        FoodGroup[] items = new FoodGroup[]{
                new FoodGroup(R.drawable.vitamins_fruit_logo, 205, "Fruits"),
                new FoodGroup(R.drawable.vitamins_fruit_logo, 304, "Vegetables"),
                new FoodGroup(R.drawable.vitamins_fruit_logo, 16, "Drinks"),
                new FoodGroup(R.drawable.vitamins_fruit_logo, 12, "Bake"),
                new FoodGroup(R.drawable.vitamins_fruit_logo, 18, "Cereals"),
                new FoodGroup(R.drawable.vitamins_fruit_logo, 104, "Dishes")
        };
        mDataset = items;
    }
}
