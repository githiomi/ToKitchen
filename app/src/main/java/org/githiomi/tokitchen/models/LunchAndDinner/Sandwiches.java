package org.githiomi.tokitchen.models.LunchAndDinner;

public class Sandwiches {

    private String sandwichName;
    private int sandwichPrice;

    public Sandwiches(String sandwichName, int sandwichPrice) {
        this.sandwichName = sandwichName;
        this.sandwichPrice = sandwichPrice;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public int getSandwichPrice() {
        return sandwichPrice;
    }

    public void setSandwichPrice(int sandwichPrice) {
        this.sandwichPrice = sandwichPrice;
    }
}
