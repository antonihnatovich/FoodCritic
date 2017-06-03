package com.example.yoant.foodcritic.adapters.rv_adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
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
    public void onBindViewHolder(final ProgramAdapter.ProgramViewHolder holder, final int position) {
        String qua = mProgramListAll.get(position).getFilters();
        holder.name.setText(mProgramListAll.get(position).getName());
        holder.description.setText(mProgramListAll.get(position).getDescription());
        holder.calories.setText(mProgramListAll.get(position).getCaloriesValue() + "");
        holder.specific.setText(qua);
        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(), "For donate users only!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseHelper db = SQLiteDatabaseHelper.getsInstance(holder.itemView.getContext());
                mProgramListAll.get(position).setCondition(-1);
                db.addOrUpdateFoodProgram(mProgramListAll.get(position));
                Toast.makeText(holder.itemView.getContext(), "Food program added to \"Rejected\"", Toast.LENGTH_SHORT).show();
            }
        });
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseHelper db = SQLiteDatabaseHelper.getsInstance(holder.itemView.getContext());
                mProgramListAll.get(position).setCondition(1);
                db.addOrUpdateFoodProgram(mProgramListAll.get(position));
                Toast.makeText(holder.itemView.getContext(), "Food program added to \"Favorite\"", Toast.LENGTH_SHORT).show();
            }
        });
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
        Button favorite;
        @BindView(R.id.item_program_button_dismiss)
        Button dismiss;
        @BindView(R.id.item_program_type_kkal)
        TextView kkalType;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
