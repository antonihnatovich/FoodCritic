package com.example.yoant.foodcritic.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.FoodProgram;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgramFragment extends Fragment {
    @BindView(R.id.item_program_specific)
    TextView textViewSpecific;
    @BindView(R.id.item_program_value_name)
    TextView textViewName;
    @BindView(R.id.item_program_value_calories)
    TextView textViewCalories;
    @BindView(R.id.item_program_value_description)
    TextView textViewDescription;

    private List<FoodProgram> data;
    private static final String KEY_TYPE = "type";
    private Unbinder mUnbinder;
    private String mType;
    private Context mContext;


    public ProgramFragment() {
    }

    public static ProgramFragment newInstance(String type) {
        ProgramFragment fragment = new ProgramFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mType = getArguments().getString(KEY_TYPE);
        data = getTmpDataForCard(10);
        mContext = getContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }


    //@Unnecessary
    private List<FoodProgram> getTmpDataForCard(int pCount){
        List<FoodProgram> list = new ArrayList<>();
        FoodProgram program;
        String name, description;
        String[] qualifiers;
        int calories;
        if(pCount <= 0)
            return list;
        for(int i = 0; i <pCount; i++){
            name = "Item" + i;
            description = "Description of " + name;
            qualifiers = new String[]{"sport", "yummy", "low-fat"};
            calories = (int)(1000 + 0.5 * i * 18);
            program = new FoodProgram(name, description, qualifiers, calories);
            list.add(program);
        }
        return list;
    }
}