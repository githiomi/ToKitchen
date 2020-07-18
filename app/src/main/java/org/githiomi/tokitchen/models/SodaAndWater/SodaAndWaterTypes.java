package org.githiomi.tokitchen.models.SodaAndWater;

public class SodaAndWaterTypes {

    private String sodaAndWaterTypeName;
    private int sodaAndWaterTypePrice;

    public SodaAndWaterTypes(String sodaAndWaterTypeName, int sodaAndWaterTypePrice) {
        this.sodaAndWaterTypeName = sodaAndWaterTypeName;
        this.sodaAndWaterTypePrice = sodaAndWaterTypePrice;
    }

    public String getSodaAndWaterTypeName() {
        return sodaAndWaterTypeName;
    }

    public void setSodaAndWaterTypeName(String sodaAndWaterTypeName) {
        this.sodaAndWaterTypeName = sodaAndWaterTypeName;
    }

    public int getSodaAndWaterTypePrice() {
        return sodaAndWaterTypePrice;
    }

    public void setSodaAndWaterTypePrice(int sodaAndWaterTypePrice) {
        this.sodaAndWaterTypePrice = sodaAndWaterTypePrice;
    }
}
