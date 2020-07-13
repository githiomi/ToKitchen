package org.githiomi.tokitchen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = SubCategoryActivity.class.getSimpleName();

//    Widgets
    @BindView(R.id.subCategoryRecyclerView) RecyclerView wSubCategoryRecyclerView;

//    Local Variables
    // The category name passed from main activity
    private String mainCategoryName;
    // For the su categories
    private List<String> subCategoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        // Binding views using butter knife
        ButterKnife.bind(this);

        // Getting the category name from the intent
        Intent fromMainActivity = getIntent();
        mainCategoryName = fromMainActivity.getStringExtra(Constants.MAIN_CATEGORY_NAME);

        // Method to pass the main category to the method to get sub-categories
        getSubCategories(mainCategoryName);
    }

//    Implementation of the method to get the subcategories of the main category
    public void getSubCategories(String mainCategoryName){


    }
}