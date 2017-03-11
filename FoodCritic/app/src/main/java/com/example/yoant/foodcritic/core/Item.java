package com.example.yoant.foodcritic.core;

public class Item {

    private long picID;
    private String name;
    private double energeticValue;
    private double protein;
    private double sugars;
    private double fat;
    private double vitA;
    private double vitB1;
    private double vitB2;
    private double vitB3;
    private double vitB5;
    private double vitB6;
    private double vitB9;
    private double vitC;
    private double vitE;
    private double vitK;
    private double calcium;
    private double iron;
    private double magnesium;
    private double phosphorus;
    private double sodium;
    private double zinc;

    public Item(long picID, String name, double energeticValue, double protein, double sugars, double fat, double vitA, double vitB1, double vitB2, double vitB3, double vitB5, double vitB6, double vitB9, double vitC, double vitE, double vitK, double calcium, double iron, double magnesium, double phosphorus, double sodium, double zinc) {
        this.picID = picID;
        this.name = name;
        this.energeticValue = energeticValue;
        this.protein = protein;
        this.sugars = sugars;
        this.fat = fat;
        this.vitA = vitA;
        this.vitB1 = vitB1;
        this.vitB2 = vitB2;
        this.vitB3 = vitB3;
        this.vitB5 = vitB5;
        this.vitB6 = vitB6;
        this.vitB9 = vitB9;
        this.vitC = vitC;
        this.vitE = vitE;
        this.vitK = vitK;
        this.calcium = calcium;
        this.iron = iron;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.sodium = sodium;
        this.zinc = zinc;
    }

    public long getPicID() {
        return picID;
    }

    public void setPicID(long picID) {
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

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getVitA() {
        return vitA;
    }

    public void setVitA(double vitA) {
        this.vitA = vitA;
    }

    public double getVitB1() {
        return vitB1;
    }

    public void setVitB1(double vitB1) {
        this.vitB1 = vitB1;
    }

    public double getVitB2() {
        return vitB2;
    }

    public void setVitB2(double vitB2) {
        this.vitB2 = vitB2;
    }

    public double getVitB3() {
        return vitB3;
    }

    public void setVitB3(double vitB3) {
        this.vitB3 = vitB3;
    }

    public double getVitB5() {
        return vitB5;
    }

    public void setVitB5(double vitB5) {
        this.vitB5 = vitB5;
    }

    public double getVitB6() {
        return vitB6;
    }

    public void setVitB6(double vitB6) {
        this.vitB6 = vitB6;
    }

    public double getVitB9() {
        return vitB9;
    }

    public void setVitB9(double vitB9) {
        this.vitB9 = vitB9;
    }

    public double getVitC() {
        return vitC;
    }

    public void setVitC(double vitC) {
        this.vitC = vitC;
    }

    public double getVitE() {
        return vitE;
    }

    public void setVitE(double vitE) {
        this.vitE = vitE;
    }

    public double getVitK() {
        return vitK;
    }

    public void setVitK(double vitK) {
        this.vitK = vitK;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

}
