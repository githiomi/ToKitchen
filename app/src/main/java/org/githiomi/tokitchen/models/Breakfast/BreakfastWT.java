package org.githiomi.tokitchen.models.Breakfast;

import java.util.List;

public class BreakfastWT {

    private String breakfastName;
    private List<BreakfastType> breakfastTypeList;

    public BreakfastWT(String breakfastName, List<BreakfastType> breakfastTypeList) {
        this.breakfastName = breakfastName;
        this.breakfastTypeList = breakfastTypeList;
    }

    public String getBreakfastName() {
        return breakfastName;
    }

    public void setBreakfastName(String breakfastName) {
        this.breakfastName = breakfastName;
    }

    public List<BreakfastType> getBreakfastTypeList() {
        return breakfastTypeList;
    }

    public void setBreakfastTypeList(List<BreakfastType> breakfastTypeList) {
        this.breakfastTypeList = breakfastTypeList;
    }
}
