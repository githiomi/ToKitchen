package org.githiomi.tokitchen.models.Drinks;

import java.util.List;

public class DrinksCategory {

    private String drinksCategoryName;
    private List<DrinksCategoryTypes> drinksCategoryTypes;

    public DrinksCategory(String drinksCategoryName, List<DrinksCategoryTypes> drinksCategoryTypes) {
        this.drinksCategoryName = drinksCategoryName;
        this.drinksCategoryTypes = drinksCategoryTypes;
    }

    public String getDrinksCategoryName() {
        return drinksCategoryName;
    }

    public void setDrinksCategoryName(String drinksCategoryName) {
        this.drinksCategoryName = drinksCategoryName;
    }

    public List<DrinksCategoryTypes> getDrinksCategoryTypes() {
        return drinksCategoryTypes;
    }

    public void setDrinksCategoryTypes(List<DrinksCategoryTypes> drinksCategoryTypes) {
        this.drinksCategoryTypes = drinksCategoryTypes;
    }
}
