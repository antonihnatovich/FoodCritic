package com.example.yoant.foodcritic.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.rv_adapters.ThreeTypesMenuAdapter;
import com.example.yoant.foodcritic.models.FoodMenuElement;
import com.example.yoant.foodcritic.view.fragments.MenuFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private List<FoodMenuElement> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_activity_container, MenuFragment.newInstance()).commit();
//        getData();
//        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.menu_activity_recycler);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ThreeTypesMenuAdapter adapter = new ThreeTypesMenuAdapter(mList, this);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);
    }
}
