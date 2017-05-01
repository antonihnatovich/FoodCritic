package com.example.yoant.foodcritic.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yoant.foodcritic.helper.constants.FoodType;
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
                return ProductsFragment.newInstance(FoodType.FRUIT);
            case 1:
                return ProductsFragment.newInstance(FoodType.VEGETABLE);
            case 2:
                return ProductsFragment.newInstance(FoodType.DRINK);
            case 3:
                return ProductsFragment.newInstance(FoodType.BAKE);
            case 4:
                return ProductsFragment.newInstance(FoodType.CEREAL);
            case 5:
                return ProductsFragment.newInstance(FoodType.DISH);
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
                return FoodType.FRUIT;
            case 1:
                return FoodType.VEGETABLE;
            case 2:
                return FoodType.DRINK;
            case 3:
                return FoodType.BAKE;
            case 4:
                return FoodType.CEREAL;
            case 5:
                return FoodType.DISH;
            default:
                return null;
        }
    }
}
