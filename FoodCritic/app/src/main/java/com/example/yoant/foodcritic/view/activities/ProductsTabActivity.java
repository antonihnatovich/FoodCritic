package com.example.yoant.foodcritic.view.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.view.fragments.FruitFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsTabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapterViewPager;
    private int[] tabIcons = {
            R.drawable.add_24dp,
            R.drawable.delete_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager1.setAdapter(adapterViewPager);


//        setUpViewPager(viewPager1);
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setUpTabIcons();
    }

    private void setUpTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

//        private final List<Fragment> mFragments = new ArrayList<>();
//        private final List<String> mFragmentsTitles = new ArrayList<>();
        private static int ITEMS_NUMBER = 2;

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return FruitFragment.newInstance(0);
                case 1:
                    return FruitFragment.newInstance(1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return ITEMS_NUMBER;
        }


        @Override
        public CharSequence getPageTitle(int position){
            return null;
        }


    }

}
