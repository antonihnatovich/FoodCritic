package com.example.yoant.foodcritic.adapters.rv_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.ItemClickListener;
import com.example.yoant.foodcritic.helper.constants.MenuConstants;
import com.example.yoant.foodcritic.helper.constants.TimeName;
import com.example.yoant.foodcritic.models.FoodMenuElement;
import com.example.yoant.foodcritic.view.activities.CreateMenuProductActivity;
import com.example.yoant.foodcritic.view.activities.CreateProductActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreeTypesMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FoodMenuElement> mList;
    private List<FoodMenuElement> mListBreakFast;
    private List<FoodMenuElement> mListLunch;
    private List<FoodMenuElement> mListDinner;
    private final Context mContext;
    private Intent mIntent;
    private FragmentManager fragmentManager;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private Intent createMenuItemIntent;
    private String mType;

    private int mDailyCalories = 0;
    private int mCurrentCalories = 0;

    public ThreeTypesMenuAdapter(List<FoodMenuElement> pList, final Context pContext, FragmentManager fragmentManager, String type, int pDailyCalories, int pDailyCurrentCalories) {
        mList = pList;
        mListBreakFast = new ArrayList<>();
        mListLunch = new ArrayList<>();
        mListDinner = new ArrayList<>();
        if(mList!= null)
        for (FoodMenuElement e : mList) {
            switch (e.getmTime()) {
                case "BREAKFAST":
                    mListBreakFast.add(e);
                    break;
                case "LUNCH":
                    mListLunch.add(e);
                    break;
                case "DINNER":
                    mListDinner.add(e);
                    break;
            }
        }
        mContext = pContext;
        mIntent = new Intent(mContext, CreateProductActivity.class);
        this.fragmentManager = fragmentManager;
        mAdapter = this;
        createMenuItemIntent = new Intent(mContext, CreateMenuProductActivity.class);
        mType = type;
        mDailyCalories = pDailyCalories;
        mCurrentCalories = pDailyCurrentCalories;
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
                    int percent = (int)((mCurrentCalories*1.0 / mDailyCalories)*100);
                    ((TimeViewHolder) holder).mPercent.setText( percent + "/100%");
                    break;
                case FoodMenuElement.FOOD_TYPE:
                    ((FoodViewHolder) holder).mName.setText(element.getmName());
                    ((FoodViewHolder) holder).mProtein.setText((int)(element.getmDailyProtein()*1.0/ MenuConstants.PROTEIN*100) + "");
                    ((FoodViewHolder) holder).mFat.setText(
                            (int)(element.getmDailyFat()*1.0/MenuConstants.FAT*100) + "");
                    ((FoodViewHolder) holder).mCarbon.setText((int)(element.getmDailyCarbon()*1.0/MenuConstants.CARBOHYDRATE*100) + "");
                    ((FoodViewHolder) holder).mEnergy.setText((int)(element.getmDailyEnergy()*1.0/MenuConstants.CALORIES*100) + "");
                    break;
                case FoodMenuElement.BUTTON_TYPE:
                    ((ButtonViewHolder) holder).mName.setText(element.getmName());
                    ((ButtonViewHolder) holder).mName.setTextColor(Color.DKGRAY);
                    ((ButtonViewHolder) holder).setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            createMenuItemIntent.putExtra("type", mType);
                            int dayTime = 0;
                            String timeString;
                            if(mList.get(position - 1).getType() == 1)timeString = mList.get(position - 1).getmName();
                            else timeString = mList.get(position - 1).getmTime();
                            if(timeString.equals(TimeName.BREAKFAST)) dayTime = 0;
                            else if(timeString.equals(TimeName.LUNCH)) dayTime = 1;
                            else dayTime = 2;
                            createMenuItemIntent.putExtra("time", dayTime);
                            mContext.startActivity(createMenuItemIntent);
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
        @BindView(R.id.menu_dish_name) TextView mName;
        @BindView(R.id.menu_dish_protein_value) TextView mProtein;
        @BindView(R.id.menu_dish_fat_value) TextView mFat;
        @BindView(R.id.menu_dish_carbon_value) TextView mCarbon;
        @BindView(R.id.menu_dish_energy_value) TextView mEnergy;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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

    public void setDailyCalories(int pDailyCalories){
        mDailyCalories = pDailyCalories;
    }

    public void setmCurrentCalories(int pCurrentCalories){
        mCurrentCalories = pCurrentCalories;
    }
}
