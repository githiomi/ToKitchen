package org.githiomi.tokitchen.models.Bakery;

public class BakedGoods {

    private String bakedGoodName;
    private int bakedGoodPrice;

    public BakedGoods(String bakedGoodName, int bakedGoodPrice) {
        this.bakedGoodName = bakedGoodName;
        this.bakedGoodPrice = bakedGoodPrice;
    }

    public String getBakedGoodName() {
        return bakedGoodName;
    }

    public void setBakedGoodName(String bakedGoodName) {
        this.bakedGoodName = bakedGoodName;
    }

    public int getBakedGoodPrice() {
        return bakedGoodPrice;
    }

    public void setBakedGoodPrice(int bakedGoodPrice) {
        this.bakedGoodPrice = bakedGoodPrice;
    }

    // To add the ksh. to the price
    @Override
    public String toString() {
        return "Ksh. " + getBakedGoodPrice();
    }
}
