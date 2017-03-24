package com.example.yoant.foodcritic.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.GroupAdapter;
import com.example.yoant.foodcritic.core.FoodGroup;
import com.example.yoant.foodcritic.view.fragments.VitaminsGroupFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VitaminsActivity extends FragmentActivity {
    public static final String TAG = "VitaminsActivity";
    private boolean mFragmentShown;
    private List<FoodGroup> mDataset = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamins);
        initialiseData();

        mRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view_activity);
        mAdapter = new GroupAdapter(mDataset);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
//        if(savedInstanceState == null){
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            VitaminsGroupFragment fragment = new VitaminsGroupFragment();
//            transaction.replace(R.id.group_content_fragment, fragment);
//            transaction.commit();
//        }

    }

    private void initialiseData() {

        mDataset = Arrays.asList(FoodGroup.items);
    }
}
