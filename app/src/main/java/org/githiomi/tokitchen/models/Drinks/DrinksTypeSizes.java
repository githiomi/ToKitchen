package org.githiomi.tokitchen.models.Drinks;

public class DrinksTypeSizes {

    private String sizeName;
    private int sizePrice;

    public DrinksTypeSizes(String sizeName, int sizePrice) {
        this.sizeName = sizeName;
        this.sizePrice = sizePrice;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(int sizePrice) {
        this.sizePrice = sizePrice;
    }

    @Override
    public String toString() {
        return "Ksh. " + sizePrice;
    }
}
