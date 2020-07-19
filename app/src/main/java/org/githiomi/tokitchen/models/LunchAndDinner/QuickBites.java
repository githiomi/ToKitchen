package org.githiomi.tokitchen.models.LunchAndDinner;

import java.util.List;

public class QuickBites {

    private String mealName;
    private List<QuickBiteTypes> quickBiteTypesList;

    public QuickBites(String mealName, List<QuickBiteTypes> quickBiteTypesList) {
        this.mealName = mealName;
        this.quickBiteTypesList = quickBiteTypesList;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public List<QuickBiteTypes> getQuickBiteTypesList() {
        return quickBiteTypesList;
    }

    public void setQuickBiteTypesList(List<QuickBiteTypes> quickBiteTypesList) {
        this.quickBiteTypesList = quickBiteTypesList;
    }
}
