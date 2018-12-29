package com.mackyc.projects.mybudget;

import java.util.Locale;

public class CostBreakdownItem {

    private Category category;
    private String categoryName;
    private double categoryCost;

    public CostBreakdownItem(Category category, double categoryCost) {
        this.categoryCost = categoryCost;
        this.category = category;
        setCategoryString();
    }

    public void setCategoryString() {
        switch (category) {
            case IMPORTANT:
                categoryName = "Important";
                break;
            case UTILITIES:
                categoryName = "Utilities";
                break;
            case FOOD:
                categoryName = "Food";
                break;
            case TRANSPORTATION:
                categoryName = "Transportation";
                break;
            case PERSONAL:
                categoryName = "Personal";
                break;
            case ENTERTAINMENT:
                categoryName = "Entertainment";
                break;
            case SUPPLIES:
                categoryName = "Supplies";
                break;
            default:
                categoryName = "Others";
        }
    }

    public double getCategoryCost() {
        return categoryCost;
    }

    public void setCategoryCost(double categoryCost) {
        this.categoryCost = categoryCost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    enum Category {IMPORTANT, UTILITIES, FOOD, TRANSPORTATION, PERSONAL, ENTERTAINMENT, SUPPLIES, OTHERS}

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
