package org.githiomi.tokitchen.models.LunchAndDinner;

public class Salads {

    private String saladName;
    private int saladPrice;

    public Salads(String saladName, int saladPrice) {
        this.saladName = saladName;
        this.saladPrice = saladPrice;
    }

    public String getSaladName() {
        return saladName;
    }

    public void setSaladName(String saladName) {
        this.saladName = saladName;
    }

    public int getSaladPrice() {
        return saladPrice;
    }

    public void setSaladPrice(int saladPrice) {
        this.saladPrice = saladPrice;
    }
}
