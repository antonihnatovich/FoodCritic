package com.example.yoant.foodcritic.adapters.curentlyused;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.FoodGroup;

public class GroupFragmentAdapter extends RecyclerView.Adapter<GroupFragmentAdapter.VViewHolder> {
    private static final String TAG = "GroupFragmentAdapter";
    private FoodGroup[] mDataSet;
    private Context mContext;
    private String imgURL = "http://hitgid.com/images/%D1%84%D1%80%D1%83%D0%BA%D1%82%D1%8B-7.jpg";

    public GroupFragmentAdapter(FoodGroup[] mDataSet, Context context) {
        this.mDataSet = mDataSet;
        mContext = context;
    }

    @Override
    public VViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vitamins_group_element_gridlayout, parent, false);
        final VViewHolder holder = new VViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GroupFragmentAdapter.VViewHolder holder, int position) {
        holder.groupItemsCount.setText("Items : " + mDataSet[position].getItemsCount());
        holder.groupName.setText(mDataSet[position].getGroupName());
        //holder.groupImageRecourse.setImageResource(mDataSet[position].getImageResource());
        Glide.with(mContext).load(imgURL).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.groupImageRecourse);
        holder.groupImageRecourse.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    private void activateClickEvenets(VViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

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
            groupImageRecourse = (ImageView) view.findViewById(R.id.vitaminsGroupElementItemLogo);
            groupItemsCount = (TextView) view.findViewById(R.id.vitaminsGroupElementItemCount);
            groupName = (TextView) view.findViewById(R.id.vitaminsGroupElementItemName);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_container);
        }
    }

}
