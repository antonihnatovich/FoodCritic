package com.example.yoant.foodcritic.adapters.rv_adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.FoodProgram;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>{

    private List<FoodProgram> mProgramListAll;
    private List<FoodProgram> mProgramListAccepted;
    private List<FoodProgram> mProgramListRejected;
    //TODO Do a ProgramAdapter in the university

    @Override
    public ProgramAdapter.ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProgramAdapter.ProgramViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_program_value_name) TextView name;
        @BindView(R.id.item_program_specific) TextView specific;
        @BindView(R.id.item_program_value_calories) TextView calories;
        @BindView(R.id.item_program_value_description) TextView description;
        @BindView(R.id.item_program_button_explore) Button explore;
        @BindView(R.id.item_program_button_share) Button share;
        @BindView(R.id.item_program_type_kkal) TextView kkalType;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
