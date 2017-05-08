package com.example.yoant.foodcritic.adapters.rv_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.ItemClickListener;
import com.example.yoant.foodcritic.models.FoodMenuElement;
import com.example.yoant.foodcritic.view.activities.CreateProductActivity;
import com.example.yoant.foodcritic.view.fragments.CreateMenuProductFragment;

import java.util.List;

public class ThreeTypesMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FoodMenuElement> mList;
    private final Context mContext;
    private Intent mIntent;
    private FragmentManager fragmentManager;

    public ThreeTypesMenuAdapter(List<FoodMenuElement> pList, final Context pContext, FragmentManager fragmentManager) {
        mList = pList;
        mContext = pContext;
        mIntent = new Intent(mContext, CreateProductActivity.class);
        this.fragmentManager = fragmentManager;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        switch (viewType) {
            case FoodMenuElement.TIME_TYPE:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.menu_timeline, parent, false);
                return new TimeViewHolder(view);
            case FoodMenuElement.FOOD_TYPE:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.menu_dish, parent, false);
                return new FoodViewHolder(view);
            case FoodMenuElement.BUTTON_TYPE:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.menu_button, parent, false);
                return new ButtonViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FoodMenuElement element = mList.get(position);
        if (element != null) {
            switch (element.getType()) {
                case FoodMenuElement.TIME_TYPE:
                    ((TimeViewHolder) holder).mName.setText(element.getmName());
                    ((TimeViewHolder) holder).mTime.setText(element.getmTime());
                    ((TimeViewHolder) holder).mPercent.setText(element.getmDailyPercent() + "/100%");
                    break;
                case FoodMenuElement.FOOD_TYPE:
                    ((FoodViewHolder) holder).mName.setText(element.getmName());
                    ((FoodViewHolder) holder).mProtein.setText(element.getmDailyProtein() + "");
                    ((FoodViewHolder) holder).mFat.setText(element.getmDailyFat() + "");
                    ((FoodViewHolder) holder).mCarbon.setText(element.getmDailyCarbon() + "");
                    ((FoodViewHolder) holder).mEnergy.setText(element.getmDailyEnergy() + "");
                    break;
                case FoodMenuElement.BUTTON_TYPE:
                    ((ButtonViewHolder) holder).mName.setText(element.getmName());
                    ((ButtonViewHolder) holder).mName.setTextColor(Color.DKGRAY);
                    ((ButtonViewHolder) holder).setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            CreateMenuProductFragment fragment = new CreateMenuProductFragment();
                            fragment.show(fragmentManager, "Kek");
                            //mContext.startActivity(mIntent);
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            FoodMenuElement element = mList.get(position);
            if (element != null)
                return element.getType();
        }
        return 0;
    }

    public void upDateData(List<FoodMenuElement> data) {
        mList = data;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mTime;
        private TextView mPercent;

        public TimeViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.menu_timeline_name);
            mTime = (TextView) itemView.findViewById(R.id.menu_timeline_time);
            mPercent = (TextView) itemView.findViewById(R.id.menu_timeline_percent);
        }
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mProtein;
        private TextView mFat;
        private TextView mCarbon;
        private TextView mEnergy;

        public FoodViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.menu_dish_name);
            mProtein = (TextView) itemView.findViewById(R.id.menu_dish_protein_value);
            mFat = (TextView) itemView.findViewById(R.id.menu_dish_fat_value);
            mCarbon = (TextView) itemView.findViewById(R.id.menu_dish_carbon_value);
            mEnergy = (TextView) itemView.findViewById(R.id.menu_dish_energy_value);
        }
    }

    public class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView mName;
        private ItemClickListener mItemClickListener;

        public ButtonViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.menu_button_add);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener pItemClickListener) {
            mItemClickListener = pItemClickListener;
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onClick(view, getLayoutPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            mItemClickListener.onClick(view, getLayoutPosition(), true);
            return true;
        }

    }
}
