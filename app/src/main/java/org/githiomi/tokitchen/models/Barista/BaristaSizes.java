package org.githiomi.tokitchen.models.Barista;

import org.parceler.Parcel;

@Parcel
public class BaristaSizes {

    // This is the class that will hold the 3 sizes of the barista drinks

    private String baristaSize;
    private int baristaPrice;

    public BaristaSizes() {
    }

    public BaristaSizes(String baristaSize, int baristaPrice) {
        this.baristaSize = baristaSize;
        this.baristaPrice = baristaPrice;
    }

    public String getBaristaSize() {
        return baristaSize;
    }

    public void setBaristaSize(String baristaSize) {
        this.baristaSize = baristaSize;
    }

    public int getBaristaPrice() {
        return baristaPrice;
    }

    public void setBaristaPrice(int baristaPrice) {
        this.baristaPrice = baristaPrice;
    }


}
