package org.githiomi.tokitchen.models.Barista;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class BaristaCategory {

    // This is the model class that will hold Coffee / Tea and its types

    private String baristaName;
    private List<BaristaType> baristaTypes;
    private Boolean isExpanded;

    public BaristaCategory() {
    }

    public BaristaCategory(String baristaName, List<BaristaType> baristaTypes, Boolean isExpanded) {
        this.baristaName = baristaName;
        this.baristaTypes = baristaTypes;
        this.isExpanded = isExpanded;
    }

    public String getBaristaName() {
        return baristaName;
    }

    public void setBaristaName(String baristaName) {
        this.baristaName = baristaName;
    }

    public List getBaristaTypes() {
        return baristaTypes;
    }

    public void setBaristaTypes(List baristaTypes) {
        this.baristaTypes = baristaTypes;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }
}
