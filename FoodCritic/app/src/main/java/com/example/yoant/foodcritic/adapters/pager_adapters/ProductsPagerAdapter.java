package com.example.yoant.foodcritic.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yoant.foodcritic.view.fragments.ProductsFragment;

public class ProductsPagerAdapter extends FragmentPagerAdapter {
    private static int NUMBER = 6;

    public ProductsPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ProductsFragment.newInstance();
            case 1:
                return ProductsFragment.newInstance();
            case 2:
                return ProductsFragment.newInstance();
            case 3:
                return ProductsFragment.newInstance();
            case 4:
                return ProductsFragment.newInstance();
            case 5:
                return ProductsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Fruits";
            case 1:
                return "Vegetables";
            case 2:
                return "Drinks";
            case 3:
                return "Bake";
            case 4:
                return "Cereals";
            case 5:
                return "Dishes";
            default:
                return null;
        }
    }
}
