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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mAdapter = new ThreeTypesMenuAdapter(mList, mContext, getFragmentManager());
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
            } //TODO add product values to percent converter
        //TODO add instant view refresher after creating new menu item
        //TODO add changing time of the dayTime parameters
        //TODO add more dayTime regime
        //TODO add total values parameters to follow list(general percent)
        int n = productsBreakfast.size(), m = productsLunch.size(), k = productsDinner.size(), timeNameCount = 3, addButtonCount = 3;
        for (int i = 0; i < n + m + k + timeNameCount + addButtonCount; i++) {
            if (i == 0)
                mList.add(new FoodMenuElement(1, "Breakfast", "8:00", 0, 0, 0, 0, 12));
            else if (i > 0 && i <= n) {
                Product p = productsBreakfast.get(i - 1);
                mList.add(new FoodMenuElement(2, p.getName(), "", (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else if (i == n + 1)
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
            else if (i == n + 2)
                mList.add(new FoodMenuElement(1, "Lunch", "14:00", 0, 0, 0, 0, 12));
            else if (i > n + 2 && i <= n + m + 2) {
                Product p = productsLunch.get(i - n - 3);
                mList.add(new FoodMenuElement(2, p.getName(), "", (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else if (i == n + m + 3)
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
            else if (i == n + m + 4)
                mList.add(new FoodMenuElement(1, "Dinner", "20:00", 0, 0, 0, 0, 12));
            else if (i > n + m + 4 && i <= n + m + k + 4) {
                Product p = productsDinner.get(i - n - m - 5);
                mList.add(new FoodMenuElement(2, p.getName(), "", (int) p.getProtein(), (int) p.getFat(), (int) p.getCarb(), (int) p.getEnergeticValue(), 1));
            } else
                mList.add(new FoodMenuElement(3, "+ Add new food", "", 0, 0, 0, 0, 0));
        }
    }
}
