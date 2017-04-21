package com.example.yoant.foodcritic.models;

import com.example.yoant.foodcritic.R;

public class Product {
    private long id;
    private int picID;
    private String name;
    private String productDescription;
    private double energeticValue;
    private double carb;
    private double protein;
    private double fat;
    private boolean isFavorite;

    public Product() {
    }

    public Product(long id, int picID, String name, String productDescription, double energeticValue, double carb, double protein, double fat, boolean isFavorite) {
        this.id = id;
        this.picID = picID;
        this.name = name;
        this.productDescription = productDescription;
        this.energeticValue = energeticValue;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.isFavorite = isFavorite;
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

    public static Product[] getProducts() {
        return products;
    }

    public static void setProducts(Product[] products) {
        Product.products = products;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public static Product[] products = new Product[]{new Product(0, R.drawable.vitamins_fruit_logo, "Banana","", 16, 17, 18, 19, false)
            , new Product(1, R.drawable.vitamins_fruit_logo, "Apple","", 12, 13, 14, 15, false),
            new Product(2, R.drawable.vitamins_fruit_logo, "Arbuz","", 20, 21, 22, 23, false),
            new Product(3, R.drawable.vitamins_fruit_logo, "Orange","", 16, 17, 18, 19, false),
            new Product(4, R.drawable.vitamins_fruit_logo, "Cherry","", 16, 17, 18, 19, false)
    };
}
