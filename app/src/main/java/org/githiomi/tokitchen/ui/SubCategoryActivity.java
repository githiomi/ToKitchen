package org.githiomi.tokitchen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.SubCategoryAdapter;
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
    // For the sub categories
    private List<String> subCategoryNames;
    // For the adapter
    private SubCategoryAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        // Binding views using butter knife
        ButterKnife.bind(this);

        // Getting the category name from the intent
        Intent fromMainActivity = getIntent();
        mainCategoryName = fromMainActivity.getStringExtra(Constants.MAIN_CATEGORY_NAME);

        // Setting the app bar to category name
        getSupportActionBar().setTitle(mainCategoryName);

        // Method to pass the main category to the method to get sub-categories
        getSubCategories(mainCategoryName);
    }

//    Implementation of the method to get the subcategories of the main category
    public void getSubCategories(String mainCategoryName) {

        try {

//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(Constants.loadJsonFromAssets(this));

            String key = "java";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();
            for (int i = 0; i < mealCategoriesCount; i += 1) {

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");

                if (categoryName.equals(mainCategoryName)) {

                    String mealCategoriesKey = "categories";
                    JSONArray breakfastCategories = mealCategory.getJSONArray(mealCategoriesKey);
                    int length = breakfastCategories.length();

                    // Initializing the array list
                    subCategoryNames = new ArrayList();

                    for (int d = 0; d < length; d += 1) {

                        JSONObject mealSubCategories = (JSONObject) breakfastCategories.get(d);

                        String forTheSubCategories = "categoryName";
                        String bCategoryName = mealSubCategories.getString(forTheSubCategories);
                        Log.d(TAG, mainCategoryName + " categories: " + bCategoryName);

                        // Adding the sub category name to the array list
                        subCategoryNames.add(bCategoryName);

                    }

                    //Passing the sub categories to the adapter
                    passToAdapter(subCategoryNames);

                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    // Method to pass the sub categories to an adapter
    public void passToAdapter(List<String> subCategoryNames){

        // Init the adapter
        subCategoryAdapter = new SubCategoryAdapter(subCategoryNames, this);

        wSubCategoryRecyclerView.setAdapter(subCategoryAdapter);
        wSubCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        wSubCategoryRecyclerView.setHasFixedSize(true);
        subCategoryAdapter.notifyDataSetChanged();

    }
}