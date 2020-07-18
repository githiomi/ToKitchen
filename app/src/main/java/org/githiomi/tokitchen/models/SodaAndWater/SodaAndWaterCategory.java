package org.githiomi.tokitchen.models.SodaAndWater;

import java.util.List;

public class SodaAndWaterCategory {

    private String sodaAndWaterCategoryName;
    private List<SodaAndWaterTypes> sodaAndWaterTypesList;

    public SodaAndWaterCategory(String sodaAndWaterCategoryName, List<SodaAndWaterTypes> sodaAndWaterTypesList) {
        this.sodaAndWaterCategoryName = sodaAndWaterCategoryName;
        this.sodaAndWaterTypesList = sodaAndWaterTypesList;
    }

    public String getSodaAndWaterCategoryName() {
        return sodaAndWaterCategoryName;
    }

    public void setSodaAndWaterCategoryName(String sodaAndWaterCategoryName) {
        this.sodaAndWaterCategoryName = sodaAndWaterCategoryName;
    }

    public List<SodaAndWaterTypes> getSodaAndWaterTypesList() {
        return sodaAndWaterTypesList;
    }

    public void setSodaAndWaterTypesList(List<SodaAndWaterTypes> sodaAndWaterTypesList) {
        this.sodaAndWaterTypesList = sodaAndWaterTypesList;
    }
}
