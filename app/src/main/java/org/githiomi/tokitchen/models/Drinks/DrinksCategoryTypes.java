package org.githiomi.tokitchen.models.Drinks;

public class DrinksCategoryTypes {

    private String drinkCategoryTypeName;
    private int drinkCategoryTypePrice;

    public DrinksCategoryTypes(String drinkCategoryTypeName, int drinkCategoryTypePrice) {
        this.drinkCategoryTypeName = drinkCategoryTypeName;
        this.drinkCategoryTypePrice = drinkCategoryTypePrice;
    }

    public String getDrinkCategoryTypeName() {
        return drinkCategoryTypeName;
    }

    public void setDrinkCategoryTypeName(String drinkCategoryTypeName) {
        this.drinkCategoryTypeName = drinkCategoryTypeName;
    }

    public int getDrinkCategoryTypePrice() {
        return drinkCategoryTypePrice;
    }

    public void setDrinkCategoryTypePrice(int drinkCategoryTypePrice) {
        this.drinkCategoryTypePrice = drinkCategoryTypePrice;
    }
}
