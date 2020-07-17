package org.githiomi.tokitchen.models.Barista;

import java.util.List;

public class BaristaType {

    // This is the class that will hold the type of coffee / tea and a class for its sizes

    private String baristaType;
    private List<BaristaSizes> baristaSizesList;
    private Boolean isExpanded;

    public BaristaType(String baristaType, List<BaristaSizes> baristaSizesList) {
        this.baristaType = baristaType;
        this.baristaSizesList = baristaSizesList;
        this.isExpanded = isExpanded;
    }

    public String getBaristaType() {
        return baristaType;
    }

    public void setBaristaType(String barista) {
        this.baristaType = barista;
    }

    public List<BaristaSizes> getBaristaSizesList() {
        return baristaSizesList;
    }

    public void setBaristaSizesList(List<BaristaSizes> baristaSizesList) {
        this.baristaSizesList = baristaSizesList;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }
}
