package com.example.yoant.foodcritic.models;

import com.example.yoant.foodcritic.R;

public class FoodGroup {
    private int imageResource;
    private int itemsCount;
    private String groupName;

    public FoodGroup() {
    }

    public static FoodGroup[] items = new FoodGroup[]{
            new FoodGroup(R.drawable.vitamins_fruit_logo, 205, "Fruits"),
            new FoodGroup(R.drawable.vitamins_vegetable_logo, 304, "Vegetables"),
            new FoodGroup(R.drawable.vitamins_drink_logo, 16, "Drinks"),
            new FoodGroup(R.drawable.vitamins_bake_logo, 12, "Bake"),
            new FoodGroup(R.drawable.vitamins_cereals_logo, 18, "Cereals"),
            new FoodGroup(R.drawable.vitamins_dishes_logo, 104, "Dishes")
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
