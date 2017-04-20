package com.example.yoant.foodcritic.models;

import com.example.yoant.foodcritic.R;

public class MainMenuElement {
    private int mElementLogoId;
    private String mElementName;
    private String mElementDescription;

    public static final MainMenuElement[] menuElements = {
            new MainMenuElement("Vitamins", "Learn more about food.", R.drawable.menu_element_icon_vitamins),
            new MainMenuElement("Food Menu", "Create menu for day/week.", R.drawable.menu_element_icon_food_menu),
            new MainMenuElement("Food Adviser", "Ask app for food help.", R.drawable.menu_element_icon_food_adviser),
            new MainMenuElement("Calories Calculator", "Count your daily calories.", R.drawable.menu_element_icon_calories_calculator),
            new MainMenuElement("Food programs", "Follow the one of the best food practice.", R.drawable.menu_element_icon_food_program),
            new MainMenuElement("Shop List", "Notice anything what you want to buy in shop here in comfy way.", R.drawable.menu_element_icon_shop_list)
    };

    private MainMenuElement(String elementName, String elementDescription, int elementLogoId){
        mElementName = elementName;
        mElementDescription = elementDescription;
        mElementLogoId = elementLogoId;
    }

    public int getmElementLogoId() {
        return mElementLogoId;
    }

    public void setmElementLogoId(int mElementLogoId) {
        this.mElementLogoId = mElementLogoId;
    }

    public String getmElementName() {
        return mElementName;
    }

    public void setmElementName(String mElementName) {
        this.mElementName = mElementName;
    }

    public String getmElementDescription() {
        return mElementDescription;
    }

    public void setmElementDescription(String mElementDescription) {
        this.mElementDescription = mElementDescription;
    }

}
