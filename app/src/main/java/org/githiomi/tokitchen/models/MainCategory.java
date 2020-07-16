package org.githiomi.tokitchen.models;

import java.util.List;

public class MainCategory {

    private String mainCategoryName;
    private List<String> subCategoryNames;
    private int mainCategoryImage;
    private boolean isExpanded;

    public MainCategory(String mainCategoryName, List<String> subCategoryNames, int mainCategoryImage) {
        this.mainCategoryName = mainCategoryName;
        this.subCategoryNames = subCategoryNames;
        this.mainCategoryImage = mainCategoryImage;
        this.isExpanded = false;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public List<String> getSubCategoryNames() {
        return subCategoryNames;
    }

    public void setSubCategoryNames(List<String> subCategoryNames) {
        this.subCategoryNames = subCategoryNames;
    }

    public int getMainCategoryImage() {
        return mainCategoryImage;
    }

    public void setMainCategoryImage(int mainCategoryImage) {
        this.mainCategoryImage = mainCategoryImage;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
