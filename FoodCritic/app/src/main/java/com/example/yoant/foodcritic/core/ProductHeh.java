package com.example.yoant.foodcritic.core;

import com.example.yoant.foodcritic.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductHeh {
    private long id;
    private int picID;
    private String name;
    private double energeticValue;
    private double carb;
    private double protein;
    private double fat;
    public static ProductHeh[] products = new ProductHeh[]{new ProductHeh(R.drawable.vitamins_fruit_logo, "Banana", 16, 17, 18, 19)
            , new ProductHeh(R.drawable.vitamins_fruit_logo, "Apple", 12, 13, 14, 15),
            new ProductHeh(R.drawable.vitamins_fruit_logo, "Arbuz", 20, 21, 22, 23),
            new ProductHeh(R.drawable.vitamins_fruit_logo, "Banana", 16, 17, 18, 19)
    };

    public ProductHeh(){}

    public ProductHeh(int picID, String name, double energeticValue, double carb, double protein, double fat) {
        this.picID = picID;
        this.name = name;
        this.energeticValue = energeticValue;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEnergeticValue() {
        return energeticValue;
    }

    public void setEnergeticValue(double energeticValue) {
        this.energeticValue = energeticValue;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
