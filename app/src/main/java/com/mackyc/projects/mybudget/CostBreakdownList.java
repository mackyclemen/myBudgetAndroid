package com.mackyc.projects.mybudget;

public class CostBreakdownList {

    private String categoryName;
    private double categoryCost;

    public CostBreakdownList(String categoryName, double categoryCost) {
        this.categoryCost = categoryCost;
        this.categoryName = categoryName;
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
