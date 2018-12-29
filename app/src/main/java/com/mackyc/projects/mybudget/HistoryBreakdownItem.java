package com.mackyc.projects.mybudget;

import com.mackyc.projects.mybudget.CostBreakdownItem.Category;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class HistoryBreakdownItem {

    private String itemName, itemCategoryStr;
    private Category itemCategory;
    private double itemCost;
    private Date itemDateTime;

    private long noteID;

    public HistoryBreakdownItem(String itemName, Category itemCategory, double itemCost) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();

        setItemCategoryString();

    }

    public HistoryBreakdownItem(String itemName, Category itemCategory, double itemCost,
                                long noteID) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemCost = itemCost;
        this.itemDateTime = new Date();
        this.noteID = noteID;

        setItemCategoryString();
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

    public String getItemDate() {
        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        sdf.applyPattern(sdf.toPattern().replaceAll("[@\\p{Alpha}]*y+[@^\\p{Alpha}]*", ""));
        return sdf.format(getItemDateTime());
    }

    public String getItemTime() {
        DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
        return df.format(getItemDateTime());
    }

    public Category getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Category itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemCategoryString() {
        switch (itemCategory) {
            case IMPORTANT:
                itemCategoryStr = "Important";
                break;
            case UTILITIES:
                itemCategoryStr = "Utilities";
                break;
            case FOOD:
                itemCategoryStr = "Food";
                break;
            case TRANSPORTATION:
                itemCategoryStr = "Transportation";
                break;
            case PERSONAL:
                itemCategoryStr = "Personal";
                break;
            case ENTERTAINMENT:
                itemCategoryStr = "Entertainment";
                break;
            case SUPPLIES:
                itemCategoryStr = "Supplies";
                break;
            default:
                itemCategoryStr = "Others";
        }
    }

    public String getItemCategoryString() {
        return itemCategoryStr;
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
}
