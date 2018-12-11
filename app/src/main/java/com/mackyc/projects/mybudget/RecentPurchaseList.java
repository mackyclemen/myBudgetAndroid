package com.mackyc.projects.mybudget;

public class RecentPurchaseList {

    private String itemName;
    private double itemCost;

    public RecentPurchaseList(String itemName, double itemCost) {
        this.itemCost = itemCost;
        this.itemName = itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
