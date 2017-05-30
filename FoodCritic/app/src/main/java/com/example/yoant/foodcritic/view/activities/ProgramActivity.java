package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.pager_adapters.ProgramPagerAdapter;
import com.example.yoant.foodcritic.helper.constants.ProgramType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramActivity extends AppCompatActivity {

    @BindView(R.id.activity_program_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.activity_program_view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_program_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        ButterKnife.bind(this);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Food Programs");

        FragmentPagerAdapter mAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.addTab(tabLayout.newTab().setText(ProgramType.general));
        tabLayout.addTab(tabLayout.newTab().setText(ProgramType.favorite));
        tabLayout.addTab(tabLayout.newTab().setText(ProgramType.rejected));
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
