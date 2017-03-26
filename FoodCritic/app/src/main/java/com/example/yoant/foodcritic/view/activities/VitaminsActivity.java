package com.example.yoant.foodcritic.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.GroupAdapter;
import com.example.yoant.foodcritic.core.FoodGroup;

import java.util.ArrayList;
import java.util.List;


public class VitaminsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamins);

//        if(savedInstanceState == null){
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            VitaminsGroupFragment fragment = new VitaminsGroupFragment();
//            transaction.replace(R.id.fragment_container, fragment);
//            transaction.commit();
//        }
    }

}
