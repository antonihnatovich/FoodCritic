package com.example.yoant.foodcritic.core;

import com.example.yoant.foodcritic.R;

public class FoodGroup {
    private int imageResource;
    private int itemsCount;
    private String groupName;

    public FoodGroup() {
    }

    public static FoodGroup[] items = new FoodGroup[]{
            new FoodGroup(R.drawable.screen, 205, "Fruits"),
            new FoodGroup(R.drawable.screen, 304, "Vegetables"),
            new FoodGroup(R.drawable.screen, 16, "Drinks"),
            new FoodGroup(R.drawable.screen, 12, "Bake"),
            new FoodGroup(R.drawable.screen, 18, "Cereals"),
            new FoodGroup(R.drawable.screen, 104, "Dishes")
    };

    public FoodGroup(int imageResource, int itemsCount, String groupName) {
        this.imageResource = imageResource;
        this.itemsCount = itemsCount;
        this.groupName = groupName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
