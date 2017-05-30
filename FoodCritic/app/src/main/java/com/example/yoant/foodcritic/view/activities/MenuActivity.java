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
import com.example.yoant.foodcritic.adapters.pager_adapters.MenuPagerAdapter;
import com.example.yoant.foodcritic.helper.constants.DayName;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.menu_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.menu_viewpager)
    ViewPager viewPager;
    @BindView(R.id.menu_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Menu's");
        FragmentPagerAdapter mAdapter = new MenuPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.addTab(tabLayout.newTab().setText(DayName.MONDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.TUESDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.WEDNESDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.THURSDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.FRIDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.SATURDAY));
        tabLayout.addTab(tabLayout.newTab().setText(DayName.SUNDAY));
        tabLayout.setupWithViewPager(viewPager);
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