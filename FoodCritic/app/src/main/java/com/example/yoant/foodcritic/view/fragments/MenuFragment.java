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
import com.example.yoant.foodcritic.helper.constants.TimeName;
import com.example.yoant.foodcritic.helper.graphic.DividerItemDecoration;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.FoodMenuElement;
import com.example.yoant.foodcritic.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private static final String KEY_TYPE = "type";
    private RecyclerView mRecyclerView;
    private ThreeTypesMenuAdapter mAdapter;
    private List<FoodMenuElement> mList;
    private Context mContext;
    private String mType;
    private boolean shouldRefreshOnResume = false;

    private int mDailyCalories = 2100;
    private int mDailyCurrentCalories  = 0;


    public MenuFragment() {
    }

    public static final MenuFragment newInstance(String pDayName) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, pDayName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler);
        mType = getArguments().getString(KEY_TYPE);
        mContext = getContext();
        getData();
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ThreeTypesMenuAdapter(mList, mContext, getFragmentManager(), mType, mDailyCalories, mDailyCurrentCalories);
        mAdapter.setDailyCalories(mDailyCalories);
        mAdapter.setmCurrentCalories(mDailyCurrentCalories);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, OrientationHelper.VERTICAL));
    }

    public void getData() {
        mList = new ArrayList<>();
        List<Product> products = SQLiteDatabaseHelper.getsInstance(getContext()).getAllMenuProductsByDayName(mType);
        List<Product> productsBreakfast = new ArrayList<>();
        List<Product> productsLunch = new ArrayList<>();
        List<Product> productsDinner = new ArrayList<>();
        for (Product product : products)
            switch (product.getTimeName()) {
                case "BREAKFAST":
                    productsBreakfast.add(product);
                    break;
                case "LUNCH":
                    productsLunch.add(product);
                    break;
                case "DINNER":
                    productsDinner.add(product);
                    break;
            }
        //TODO add more dayTime regime
        //TODO add progress bar and perform all strong operations in the background
        int n = productsBreakfast.size(), m = productsLunch.size(), k = productsDinner.size(), timeNameCount = 3, addButtonCount = 3;
        for (int i = 0; i < n + m + k + timeNameCount + addButtonCount; i++) {
            if (i == 0)
                mList.add(new FoodMenuElement(1, TimeName.BREAKFAST, "8:00", 0, 0, 0, 0, 12));
            else if (i > 0 && i <= n) {
                Product p = productsBreakfast.get(i - 1);
                mDailyCurrentCalories += p.getEnergeticValue();
                mList.add(new FoodMenuElement(2, p.getName(), TimeName.BREAKFAST, (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else if (i == n + 1)
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
            else if (i == n + 2)
                mList.add(new FoodMenuElement(1, TimeName.LUNCH, "14:00", 0, 0, 0, 0, 12));
            else if (i > n + 2 && i <= n + m + 2) {
                Product p = productsLunch.get(i - n - 3);
                mDailyCurrentCalories += p.getEnergeticValue();
                mList.add(new FoodMenuElement(2, p.getName(), TimeName.LUNCH, (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else if (i == n + m + 3)
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
            else if (i == n + m + 4)
                mList.add(new FoodMenuElement(1, TimeName.DINNER, "20:00", 0, 0, 0, 0, 12));
            else if (i > n + m + 4 && i <= n + m + k + 4) {
                Product p = productsDinner.get(i - n - m - 5);
                mDailyCurrentCalories += p.getEnergeticValue();
                mList.add(new FoodMenuElement(2, p.getName(), TimeName.DINNER, (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        shouldRefreshOnResume = true;
    }


    //TODO Optimise usage of resources. Think about proper refreshing algorithm.
    @Override
    public void onResume() {
        super.onResume();
        if(shouldRefreshOnResume){
            mDailyCurrentCalories = 0;
            getData();
            mAdapter = new ThreeTypesMenuAdapter(mList, mContext, getFragmentManager(), mType, mDailyCalories, mDailyCurrentCalories);
            mAdapter.setDailyCalories(mDailyCalories);
            mAdapter.setmCurrentCalories(mDailyCurrentCalories);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, OrientationHelper.VERTICAL));
            shouldRefreshOnResume = false;
        }
    }
}
