package com.example.yoant.foodcritic.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.rv_adapters.ThreeTypesMenuAdapter;
import com.example.yoant.foodcritic.helper.graphic.DividerItemDecoration;
import com.example.yoant.foodcritic.models.FoodMenuElement;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private static final String KEY_TYPE = "type";
    private RecyclerView mRecyclerView;
    private ThreeTypesMenuAdapter mAdapter;
    private List<FoodMenuElement> mList;
    private Context mContext;
    private String mType;
    private static String mDayName;

    public MenuFragment() {
    }

    public static final MenuFragment newInstance(String pDayName){
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        mDayName = pDayName;
        args.putString(KEY_TYPE, mDayName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.menu_recycler);
        mType = getArguments().getString(KEY_TYPE);
        getData();
        mContext = getContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ThreeTypesMenuAdapter(mList, mContext, getFragmentManager());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, OrientationHelper.VERTICAL));
    }



    public void getData(){
        mList = new ArrayList<>();
        mList.add(new FoodMenuElement(1, "Breakfast", "8:00", 0, 0, 0, 0, 12));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
        mList.add(new FoodMenuElement(1, "Lunch", "14:00", 0, 0, 0, 0, 12));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
        mList.add(new FoodMenuElement(1, "Evening", "20:00", 0, 0, 0, 0, 12));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(2, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
    }

}
