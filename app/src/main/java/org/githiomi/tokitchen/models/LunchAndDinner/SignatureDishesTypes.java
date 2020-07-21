package org.githiomi.tokitchen.models.LunchAndDinner;

public class SignatureDishesTypes {

    private String sDishTypeName;
    private int sDishTypePrice;

    public SignatureDishesTypes(String sDishTypeName, int sDishTypePrice) {
        this.sDishTypeName = sDishTypeName;
        this.sDishTypePrice = sDishTypePrice;
    }

    public String getsDishTypeName() {
        return sDishTypeName;
    }

    public void setsDishTypeName(String sDishTypeName) {
        this.sDishTypeName = sDishTypeName;
    }

    public int getsDishTypePrice() {
        return sDishTypePrice;
    }

    public void setsDishTypePrice(int sDishTypePrice) {
        this.sDishTypePrice = sDishTypePrice;
    }
}
