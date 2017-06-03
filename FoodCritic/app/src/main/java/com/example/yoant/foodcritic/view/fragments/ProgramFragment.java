package com.example.yoant.foodcritic.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.rv_adapters.ProgramAdapter;
import com.example.yoant.foodcritic.helper.constants.ProgramType;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.FoodProgram;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgramFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.fragment_program_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_program_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.fragment_program_content_view)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<FoodProgram> mData;
    private static final String KEY_TYPE = "type";
    private Unbinder mUnbinder;
    private String mType;
    private Context mContext;
    private SQLiteDatabaseHelper mDatabase;
    private static final String URL_STRING = "http://localhost:8080/programs";

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
        mData = getData(mType);
        if (mType.equals(ProgramType.favorite))
            mData = getFavoriteList(mData);
        else if (mType.equals(ProgramType.rejected))
            mData = getRejectedList(mData);
        mContext = getContext();
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgramAdapter adapter = new ProgramAdapter(mData);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private List<FoodProgram> getData(String type) {
        List<FoodProgram> list = new ArrayList<>();
        int regime = type.equals(ProgramType.rejected) ? -1 : (type.equals(ProgramType.general) ? 0 : 1);
        mData = SQLiteDatabaseHelper.getsInstance(this.getContext()).getAllFoodProgramList();
        if (regime == 0)
            return mData;
        for (FoodProgram foodProgram : mData)
            if (foodProgram.getCondition() == regime)
                list.add(foodProgram);
        return list;
    }

    //@Deprecated
    private List<FoodProgram> getTmpDataForCard(int pCount) {
        List<FoodProgram> list = new ArrayList<>();
        FoodProgram program;
        String name, description;
        String qualifiers;
        int calories;
        int whereToShow;
        if (pCount <= 0)
            return list;
        for (int i = 1; i <= pCount; i++) {
            name = "Item" + i;
            description = "Description of " + name;
            qualifiers = "sport | yummy | low-fat";
            calories = (int) (1000 + 0.5 * i * 18);
            program = new FoodProgram(name, description, qualifiers, calories, i % 2 == 0 ? 1 : (i % 3 == 0 ? -1 : 0));
            list.add(program);
        }
        return list;
    }

    private List<FoodProgram> getFavoriteList(List<FoodProgram> list) {
        List<FoodProgram> listToShow = new ArrayList<>();
        for (FoodProgram program : list)
            if (program.getCondition() == 1)
                listToShow.add(program);
        return listToShow;
    }

    private List<FoodProgram> getRejectedList(List<FoodProgram> list) {
        List<FoodProgram> listToShow = new ArrayList<>();
        for (FoodProgram program : list)
            if (program.getCondition() == -1)
                listToShow.add(program);
        return listToShow;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new RetrieveFoodProgramTask().execute();
        swipeRefreshLayout.setRefreshing(false);
    }

    private class RetrieveFoodProgramTask extends AsyncTask<Void, Void, List<FoodProgram>> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

        @Override
        protected List<FoodProgram> doInBackground(Void... urls) {
            String fakeJSON = "[{\"name\":\"Batyanskaya0\", \"description\":\"Mega description\", \"filters\":\"lol0 / kek0 / cheburek0\", \"calories\":900, \"condition\":0}," +
                    " {\"name\":\"Batyanskaya1\", \"description\":\"Mega description\", \"filters\":\"lol1 / kek1 / cheburek1\", \"calories\":901, \"condition\":0}]";
            try {
                //URL url = new URL(URL_STRING);
                // HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                try {
                    //  BufferedReader bufferedReader = new BufferedReader(
                    //   new InputStreamReader(urlConnection.getInputStream()));
                    // StringBuilder builder = new StringBuilder();
                    //   String line = null;
                    //   while((line = bufferedReader.readLine()) != null)
                    //      builder.append(line).append('\n');
                    //   bufferedReader.close();
                    //List<FoodProgram> programs = GsonHelper.toList(builder.toString(), FoodProgram.class);
                    //List<FoodProgram> programs = GsonHelper.toList(fakeJSON, FoodProgram.class);
                    FoodProgram[] programsArray = new Gson().fromJson(fakeJSON, FoodProgram[].class);

                    //  urlConnection.disconnect();
                    return Arrays.asList(programsArray);
                } finally {
                    //  urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("String to URL", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<FoodProgram> programs) {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if (programs == null)
                programs = new ArrayList<>();
            ProgramAdapter adapter = new ProgramAdapter(programs);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}