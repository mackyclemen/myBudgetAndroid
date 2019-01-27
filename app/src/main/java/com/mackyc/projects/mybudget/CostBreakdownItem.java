package com.mackyc.projects.mybudget;

public class CostBreakdownItem {

    static final int CATEGORY_IMPORTANT = 0;
    static final int CATEGORY_UTILITIES = 1;
    static final int CATEGORY_FOOD = 2;
    static final int CATEGORY_TRANSPORTATION = 3;
    static final int CATEGORY_PERSONAL = 4;
    static final int CATEGORY_ENTERTAINMENT = 5;
    static final int CATEGORY_SUPPLIES = 6;
    static final int CATEGORY_OTHERS = 7;



    static final boolean TYPE_CREDIT = false;
    static final boolean TYPE_SAVINGS = true;

    private boolean breakdownType;
    private int category;
    private double categoryCost;

    public CostBreakdownItem(int category, double categoryCost) {
        this.categoryCost = categoryCost;
        this.category = category;
        this.breakdownType = TYPE_CREDIT;
    }

    public CostBreakdownItem(int category, double categoryCost, boolean breakdownType) {
        this.categoryCost = categoryCost;
        this.category = category;
        this.breakdownType = breakdownType;
    }

    public double getCategoryCost() {
        return categoryCost;
    }

    public void setCategoryCost(double categoryCost) {
        this.categoryCost = categoryCost;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    // enum Category {IMPORTANT, UTILITIES, FOOD, TRANSPORTATION, PERSONAL, ENTERTAINMENT, SUPPLIES, OTHERS}

}
