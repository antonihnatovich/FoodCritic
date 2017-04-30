package com.example.yoant.foodcritic.models;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuElement {
    public static final int TIME_TYPE = 1;
    public static final int FOOD_TYPE = 2;
    public static final int BUTTON_TYPE = 3;

    private String mName;
    private String mTime;
    private int mType;
    private int mDailyProtein;
    private int mDailyFat;
    private int mDailyCarbon;
    private int mDailyEnergy;
    private int mDailyPercent;

    public FoodMenuElement(int mType, String mName, String mTime, int mDailyProtein, int mDailyFat, int mDailyCarbon, int mDailyEnergy, int mDailyPercent) {
        this.mType = mType;
        this.mName = mName;
        this.mTime = mTime;
        this.mDailyProtein = mDailyProtein;
        this.mDailyFat = mDailyFat;
        this.mDailyCarbon = mDailyCarbon;
        this.mDailyEnergy = mDailyEnergy;
        this.mDailyPercent = mDailyPercent;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public int getmDailyProtein() {
        return mDailyProtein;
    }

    public void setmDailyProtein(int mDailyProtein) {
        this.mDailyProtein = mDailyProtein;
    }

    public int getmDailyFat() {
        return mDailyFat;
    }

    public void setmDailyFat(int mDailyFat) {
        this.mDailyFat = mDailyFat;
    }

    public int getmDailyCarbon() {
        return mDailyCarbon;
    }

    public void setmDailyCarbon(int mDailyCarbon) {
        this.mDailyCarbon = mDailyCarbon;
    }

    public int getmDailyEnergy() {
        return mDailyEnergy;
    }

    public void setmDailyEnergy(int mDailyEnergy) {
        this.mDailyEnergy = mDailyEnergy;
    }

    public int getmDailyPercent() {
        return mDailyPercent;
    }

    public void setmDailyPercent(int mDailyPercent) {
        this.mDailyPercent = mDailyPercent;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public static List<FoodMenuElement> getData(){
        List<FoodMenuElement> list = new ArrayList<>();
        list.add(new FoodMenuElement(TIME_TYPE, "Breakfast", "8:00", 0, 0, 0, 0, 12));
        list.add(new FoodMenuElement(FOOD_TYPE, "Ovsyanochka", "", 5, 5, 5, 5, 0));
        list.add(new FoodMenuElement(BUTTON_TYPE, "+ Add new food", "", 0, 0, 0, 0, 0));

        return list;
    }
}
