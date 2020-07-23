package org.githiomi.tokitchen.models.Breakfast;

public class BreakfastType {

    private String typeName;
    private int typePrice;

    public BreakfastType(String typeName, int typePrice) {
        this.typeName = typeName;
        this.typePrice = typePrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(int typePrice) {
        this.typePrice = typePrice;
    }
}
