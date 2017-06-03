package com.example.yoant.foodcritic.models;

import com.google.gson.annotations.SerializedName;

public class FoodProgram {

    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("filters")
    private String mFilters;
    @SerializedName("calories")
    private int mCaloriesValue;
    @SerializedName("condition")
    private int mCondition;

    public FoodProgram(String pName, String pDescription, String pFilters, int pCaloriesValue, int pCondtion) {
        this.mName = pName;
        this.mDescription = pDescription;
        this.mFilters = pFilters;
        this.mCaloriesValue = pCaloriesValue;
        mCondition = pCondtion;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        mDescription = pDescription;
    }

    public String getFilters() {
        return mFilters;
    }

    public void setFilters(String pFilters) {
        mFilters = pFilters;
    }

    public int getCaloriesValue() {
        return mCaloriesValue;
    }

    public void setCaloriesValue(int pCaloriesValue) {
        mCaloriesValue = pCaloriesValue;
    }

    public int getCondition() {
        return mCondition;
    }

    public void setCondition(int pCondition) {
        mCondition = pCondition;
    }
}
