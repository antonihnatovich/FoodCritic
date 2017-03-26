package com.example.yoant.foodcritic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.core.FoodGroup;

import java.util.Arrays;
import java.util.List;

public class VitaminsRecyclerViewAdapter extends RecyclerView.Adapter<VitaminsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private FoodGroup[] mValues;
    private SparseBooleanArray selectedItems;
    private SparseBooleanArray animationItemsIndexes;
    private boolean reverceAllAnimations = false;
    private static int currentlySelectedIndex = -1;

    public VitaminsRecyclerViewAdapter(FoodGroup[] items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_vitamins, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        FoodGroup foodGroup = mValues[position];
        holder.groupName.setText(foodGroup.getGroupName());
        holder.groupImage.setImageResource(foodGroup.getImageResource());
        holder.itemsCount.setText(""+foodGroup.getItemsCount());

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener)
//                    mListener.onListFragmentInteraction(holder.mItem);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView groupName, itemsCount;
        public ImageView groupImage;
        public LinearLayout groupContainer;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            groupName = (TextView) view.findViewById(R.id.menu_element_name);
            itemsCount = (TextView) view.findViewById(R.id.menu_element_description);
            groupImage = (ImageView) view.findViewById(R.id.menu_element_icon);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
