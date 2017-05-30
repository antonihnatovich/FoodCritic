package com.example.yoant.foodcritic.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.rv_adapters.ProgramAdapter;
import com.example.yoant.foodcritic.helper.constants.ProgramType;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.FoodProgram;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgramFragment extends Fragment {
    @BindView(R.id.fragment_program_recycler_view)
    RecyclerView mRecyclerView;

    private List<FoodProgram> mData;
    private static final String KEY_TYPE = "type";
    private Unbinder mUnbinder;
    private String mType;
    private Context mContext;
    private SQLiteDatabaseHelper mDatabase;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mType = getArguments().getString(KEY_TYPE);
        mData = getTmpDataForCard(10);
        if(mType.equals(ProgramType.favorite))
            mData = getFavoriteList(mData);
        else if(mType.equals(ProgramType.rejected))
            mData = getRejectedList(mData);
        mContext = getContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgramAdapter adapter = new ProgramAdapter(mData);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    //@Unnecessary
    private List<FoodProgram> getTmpDataForCard(int pCount) {
        List<FoodProgram> list = new ArrayList<>();
        FoodProgram program;
        String name, description;
        String[] qualifiers;
        int calories;
        boolean isFavorite;
        boolean isRejected;
        if (pCount <= 0)
            return list;
        for (int i = 1; i <= pCount; i++) {
            name = "Item" + i;
            description = "Description of " + name;
            qualifiers = new String[]{"sport", "yummy", "low-fat"};
            calories = (int) (1000 + 0.5 * i * 18);
            isFavorite = i % 3 == 0;
            isRejected = i % 5 == 0;
            program = new FoodProgram(name, description, qualifiers, calories, isFavorite, isRejected);
            list.add(program);
        }
        return list;
    }

    private List<FoodProgram> getFavoriteList(List<FoodProgram> list){
        List<FoodProgram> listToShow = new ArrayList<>();
        for(FoodProgram program : list)
            if(program.isFavorite())
                listToShow.add(program);
        return listToShow;
    }

    private List<FoodProgram> getRejectedList(List<FoodProgram> list){
        List<FoodProgram> listToShow = new ArrayList<>();
        for(FoodProgram program : list)
            if(program.isRejected())
                listToShow.add(program);
        return listToShow;
    }
}