package com.mackyc.projects.mybudget;

import java.util.Date;

public class HistoryBreakdownItem {

    private final int itemID;
    private String itemName;
    private int itemCategory;
    private double itemCost;
    private Date itemDateTime;
    private boolean isCredit = true;

    private long noteID;

    public HistoryBreakdownItem(int itemID, String itemName, int itemCategory, double itemCost) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();
    }

    public HistoryBreakdownItem(int itemID, String itemName, int itemCategory, double itemCost,
                                long noteID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();
        this.noteID = noteID;
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

    public long getNoteID() {
        return noteID;
    }

    public void setNoteID(long noteID) {
        this.noteID = noteID;
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
