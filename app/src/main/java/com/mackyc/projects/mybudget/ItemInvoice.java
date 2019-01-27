package com.mackyc.projects.mybudget;

import java.util.Date;

public class ItemInvoice {

    private final int itemID;
    private String itemName;
    private int itemCategory;
    private double itemCost;
    private Date itemDateTime;
    private boolean isCredit = true;

    private String note;

    public ItemInvoice(int itemID, String itemName, int itemCategory, double itemCost) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();
    }

    public ItemInvoice(int itemID, String itemName, int itemCategory, double itemCost, String itemNote) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();
        this.note = itemNote;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getItemDateTime() {
        return itemDateTime;
    }

    public void setItemDateTime(Date itemDateTime) {
        this.itemDateTime = itemDateTime;
    }

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }

    public int getItemID() {
        return itemID;
    }
}
