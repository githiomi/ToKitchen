package org.githiomi.tokitchen.models.Drinks;

import java.util.List;

public class DrinkCategoryTypesWithSizes {

    private String drinkCategoryTypeWithSizeName;
    private List<DrinksTypeSizes> drinksTypeSizesList;

    public DrinkCategoryTypesWithSizes(String drinkCategoryTypeWithSizeName, List<DrinksTypeSizes> drinksTypeSizesList) {
        this.drinkCategoryTypeWithSizeName = drinkCategoryTypeWithSizeName;
        this.drinksTypeSizesList = drinksTypeSizesList;
    }

    public String getDrinkCategoryTypeWithSizeName() {
        return drinkCategoryTypeWithSizeName;
    }

    public void setDrinkCategoryTypeWithSizeName(String drinkCategoryTypeWithSizeName) {
        this.drinkCategoryTypeWithSizeName = drinkCategoryTypeWithSizeName;
    }

    public List<DrinksTypeSizes> getDrinksTypeSizesList() {
        return drinksTypeSizesList;
    }

    public void setDrinksTypeSizesList(List<DrinksTypeSizes> drinksTypeSizesList) {
        this.drinksTypeSizesList = drinksTypeSizesList;
    }
}
