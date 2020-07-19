package org.githiomi.tokitchen.models.LunchAndDinner;

public class QuickBiteTypes {

    private String quickBiteTypeName;
    private int quickBiteTypePrice;

    public QuickBiteTypes(String quickBiteTypeName, int quickBiteTypePrice) {
        this.quickBiteTypeName = quickBiteTypeName;
        this.quickBiteTypePrice = quickBiteTypePrice;
    }

    public String getQuickBiteTypeName() {
        return quickBiteTypeName;
    }

    public void setQuickBiteTypeName(String quickBiteTypeName) {
        this.quickBiteTypeName = quickBiteTypeName;
    }

    public int getQuickBiteTypePrice() {
        return quickBiteTypePrice;
    }

    public void setQuickBiteTypePrice(int quickBiteTypePrice) {
        this.quickBiteTypePrice = quickBiteTypePrice;
    }

    // Method to add Ksh. to the price


    @Override
    public String toString() {
        return "Ksh. " + getQuickBiteTypePrice();
    }
}
