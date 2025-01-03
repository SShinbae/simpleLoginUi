package com.example.viewsanduielement;

public class Expense {
    private String strExpName;
    private String strExpDate;
    private float strExpValue;
    private int strExpQty;
    private float strExpTotal;

    public Expense(String strExpName, String strExpDate, float strExpValue, int strExpQty) {
        this.strExpName = strExpName;
        this.strExpDate = strExpDate;
        this.strExpValue = strExpValue;
        this.strExpQty = strExpQty;
        this.strExpTotal = strExpQty * strExpValue;
    }

    public String getStrExpName() {
        return strExpName;
    }

    public void setStrExpName(String strExpName) {
        this.strExpName = strExpName;
    }

    public String getStrExpDate() {
        return strExpDate;
    }

    public void setStrExpDate(String strExpDate) {
        this.strExpDate = strExpDate;
    }

    public float getStrExpValue() {
        return strExpValue;
    }

    public void setStrExpValue(float strExpValue) {
        this.strExpValue = strExpValue;
    }

    public int getStrExpQty() {
        return strExpQty;
    }

    public void setStrExpQty(int strExpQty) {
        this.strExpQty = strExpQty;
    }

    public float getStrExpTotal() {
        return strExpTotal;
    }

    public void setStrExpTotal(float strExpTotal) {
        this.strExpTotal = strExpTotal;
    }
}