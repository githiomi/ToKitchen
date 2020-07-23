package org.githiomi.tokitchen.models.Breakfast;

public class Breakfast {

    private String mealName;
    private int mealPrice;

    public Breakfast(String mealName, int mealPrice) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(int mealPrice) {
        this.mealPrice = mealPrice;
    }
}
