package com.example.yoant.foodcritic.adapters;

import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.core.FoodGroup;

public class GroupFragmentAdapter extends RecyclerView.Adapter<GroupFragmentAdapter.VViewHolder> {
    private static final String TAG = "GroupFragmentAdapter";
    private FoodGroup[] mDataSet;

    public GroupFragmentAdapter(FoodGroup[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public VViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vitamins_group_element_gridlayout, parent, false);
        final VViewHolder holder = new VViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GroupFragmentAdapter.VViewHolder holder, int position) {
        holder.groupItemsCount.setText("Items : "+mDataSet[position].getItemsCount());
        holder.groupName.setText(mDataSet[position].getGroupName());
        holder.groupImageRecourse.setImageResource(mDataSet[position].getImageResource());
        holder.groupImageRecourse.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    private void activateClickEvenets(VViewHolder holder, final int position){
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                
            }
        });
    }

    public static class VViewHolder extends RecyclerView.ViewHolder {
        private final ImageView groupImageRecourse;
        private final TextView groupName, groupItemsCount;
        private final RelativeLayout relativeLayout;

        public VViewHolder(View view) {
            super(view);

//            groupImageRecourse = (ImageView) view.findViewById(R.id.menu_element_icon);
//            groupName = (TextView) view.findViewById(R.id.menu_element_name);
//            groupItemsCount = (TextView) view.findViewById(R.id.menu_element_description);
            groupImageRecourse = (ImageView)view.findViewById(R.id.vitaminsGroupElementItemLogo);
            groupItemsCount = (TextView)view.findViewById(R.id.vitaminsGroupElementItemCount);
            groupName = (TextView)view.findViewById(R.id.vitaminsGroupElementItemName);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.relative_layout_container);
        }

        public RelativeLayout getRelativeLayout() {
            return relativeLayout;
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
