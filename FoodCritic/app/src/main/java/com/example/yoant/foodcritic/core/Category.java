package com.example.yoant.foodcritic.core;

public class Category {
    private int picId;
    private String name;
    private int itemsCount;

    public Category(String name, int picId, int itemsCount) {
        this.name = name;
        this.itemsCount = itemsCount;
        this.picId = picId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

}
