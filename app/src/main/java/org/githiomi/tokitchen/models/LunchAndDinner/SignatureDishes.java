package org.githiomi.tokitchen.models.LunchAndDinner;

import java.util.List;

public class SignatureDishes {

    private String sDishName;
    private List<SignatureDishesTypes> signatureDishesTypesList;

    public SignatureDishes(String sDishName, List<SignatureDishesTypes> signatureDishesTypesList) {
        this.sDishName = sDishName;
        this.signatureDishesTypesList = signatureDishesTypesList;
    }

    public String getsDishName() {
        return sDishName;
    }

    public void setsDishName(String sDishName) {
        this.sDishName = sDishName;
    }

    public List<SignatureDishesTypes> getSignatureDishesTypesList() {
        return signatureDishesTypesList;
    }

    public void setSignatureDishesTypesList(List<SignatureDishesTypes> signatureDishesTypesList) {
        this.signatureDishesTypesList = signatureDishesTypesList;
    }
}
