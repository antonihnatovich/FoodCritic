package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.pager_adapters.ProductsPagerAdapter;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar mToolbar;
        ViewPager mViewPager;
        FragmentPagerAdapter mFragmentPagerAdapter;
        TabLayout mTabLayout;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mToolbar   = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.vpPager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mFragmentPagerAdapter = new ProductsPagerAdapter(getSupportFragmentManager());

        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Products Information");

        mViewPager.setAdapter(mFragmentPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText("Fruits"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Vegetables"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Cereals"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Bake"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Drinks"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Dishes"));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
