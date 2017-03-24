package com.example.yoant.foodcritic.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.core.FoodGroup;

public class GroupFragmentAdapter extends RecyclerView.Adapter<GroupFragmentAdapter.ViewHolder> {
    private static final String TAG = "GroupFragmentAdapter";
    private FoodGroup[] mDataSet;

    public GroupFragmentAdapter(FoodGroup[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public GroupFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupFragmentAdapter.ViewHolder holder, int position) {
        holder.groupItemsCount.setText(Integer.toString(mDataSet[position].getItemsCount()));
        holder.groupName.setText(mDataSet[position].getGroupName());
        holder.groupImageRecourse.setImageResource(mDataSet[position].getImageResource());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView groupImageRecourse;
        private final TextView groupName, groupItemsCount;


        public ViewHolder(View view) {
            super(view);

            groupImageRecourse = (ImageView) view.findViewById(R.id.menu_element_icon);
            groupName = (TextView) view.findViewById(R.id.menu_element_name);
            groupItemsCount = (TextView) view.findViewById(R.id.menu_element_description);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.d(TAG, "ELEMENT " + getAdapterPosition() + " CLICKED.");
                }
            });
        }

        public ImageView getGroupImageRecourse() {
            return groupImageRecourse;
        }

        public TextView getGroupName() {
            return groupName;
        }

        public TextView getGroupItemsCount() {
            return groupItemsCount;
        }
    }

}
