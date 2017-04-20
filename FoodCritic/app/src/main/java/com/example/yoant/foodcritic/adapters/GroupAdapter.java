package com.example.yoant.foodcritic.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.FoodGroup;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MViewHolder> {

    private List<FoodGroup> mDataset;

    public GroupAdapter(List<FoodGroup> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_element, parent, false);
        return new MViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {

        FoodGroup foodGroup = mDataset.get(position);
        holder.groupName.setText(foodGroup.getGroupName());
        holder.groupImage.setImageResource(foodGroup.getImageResource());
        holder.groupItemsCount.setText(Integer.toString(foodGroup.getItemsCount()));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        public ImageView groupImage;
        public TextView groupName, groupItemsCount;

        public MViewHolder(View itemView) {
            super(itemView);
            groupImage = (ImageView) itemView.findViewById(R.id.menu_element_icon);
            groupName = (TextView) itemView.findViewById(R.id.menu_element_name);
            groupItemsCount = (TextView) itemView.findViewById(R.id.menu_element_description);
        }

        @Override
        public String toString(){
            return "Hello";
        }
    }
}
