package org.githiomi.tokitchen.models;

public class MainCategory {

    private String mainCategoryName;
    private int mainCategoryImage;

    public MainCategory(String mainCategoryName, int mainCategoryImage) {
        this.mainCategoryName = mainCategoryName;
        this.mainCategoryImage = mainCategoryImage;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public int getMainCategoryImage() {
        return mainCategoryImage;
    }

    public void setMainCategoryImage(int mainCategoryImage) {
        this.mainCategoryImage = mainCategoryImage;
    }
}
