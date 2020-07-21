package org.githiomi.tokitchen.models.LunchAndDinner;

public class Wraps {

    private String wrapName;
    private int wrapPrice;

    public Wraps(String wrapName, int wrapPrice) {
        this.wrapName = wrapName;
        this.wrapPrice = wrapPrice;
    }

    public String getWrapName() {
        return wrapName;
    }

    public void setWrapName(String wrapName) {
        this.wrapName = wrapName;
    }

    public int getWrapPrice() {
        return wrapPrice;
    }

    public void setWrapPrice(int wrapPrice) {
        this.wrapPrice = wrapPrice;
    }
}
