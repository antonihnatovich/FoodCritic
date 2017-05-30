package com.example.yoant.foodcritic.adapters.rv_adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.FoodProgram;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {

    private List<FoodProgram> mProgramListAll;
    private List<FoodProgram> mProgramListAccepted;
    private List<FoodProgram> mProgramListRejected;

    public ProgramAdapter(List<FoodProgram> pData) {
        mProgramListAll = pData;
    }

    @Override
    public ProgramAdapter.ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_item, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramAdapter.ProgramViewHolder holder, int position) {
        String[] qua = mProgramListAll.get(position).getQualifiers();
        holder.name.setText(mProgramListAll.get(position).getName());
        holder.description.setText(mProgramListAll.get(position).getDescription());
        holder.calories.setText(mProgramListAll.get(position).getCaloriesValue() + "");
        holder.specific.setText(qua[0] + " \\ " + qua[1] + " \\ " + qua[2]);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mProgramListAll.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_program_value_name)
        TextView name;
        @BindView(R.id.item_program_specific)
        TextView specific;
        @BindView(R.id.item_program_value_calories)
        TextView calories;
        @BindView(R.id.item_program_value_description)
        TextView description;
        @BindView(R.id.item_program_button_explore)
        Button explore;
        @BindView(R.id.item_program_button_share)
        Button share;
        @BindView(R.id.item_program_type_kkal)
        TextView kkalType;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
