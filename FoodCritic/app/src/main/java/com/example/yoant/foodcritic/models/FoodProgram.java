package com.example.yoant.foodcritic.models;

public class FoodProgram {

    private String mName;
    private String mDescription;
    private String[] mQualifiers;
    private int mCaloriesValue;

    public FoodProgram(String mName, String mDescription, String[] mQualifiers, int mCaloriesValue) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mQualifiers = mQualifiers;
        this.mCaloriesValue = mCaloriesValue;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String[] getQualifiers() {
        return mQualifiers;
    }

    public void setQualifiers(String[] mQualifiers) {
        this.mQualifiers = mQualifiers;
    }

    public int getCaloriesValue() {
        return mCaloriesValue;
    }

    public void setCaloriesValue(int mCaloriesValue) {
        this.mCaloriesValue = mCaloriesValue;
    }
}
