package org.githiomi.tokitchen.models.Barista;

import java.util.List;

public class BaristaType {

    // This is the class that will hold the type of coffee / tea and a class for its sizes

    private String barista;
    private List<BaristaSizes> baristaSizesList;
    private Boolean isExpanded;

    public BaristaType(String barista, List<BaristaSizes> baristaSizesList, Boolean isExpanded) {
        this.barista = barista;
        this.baristaSizesList = baristaSizesList;
        this.isExpanded = isExpanded;
    }

    public String getBarista() {
        return barista;
    }

    public void setBarista(String barista) {
        this.barista = barista;
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
