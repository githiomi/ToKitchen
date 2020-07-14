package org.githiomi.tokitchen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.MainCategoryAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = MainActivity.class.getSimpleName();

//    Widgets
    @BindView(R.id.categoryRecyclerView) RecyclerView wRecyclerView;

//    Local variables
    // Array adapter
    private MainCategoryAdapter mainCategoryAdapter;
    // Category names
    private List<String> categoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Binding with butter knife
        ButterKnife.bind(this);

//        Method that will  the json file
        readJsonFile();
    }

//    Method to perform the call to read JSON data
    public void readJsonFile() {

        try {
//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(Constants.loadJsonFromAssets(this));

//            The key for the content I want to get
            String key = "java";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();

            categoryNames = new ArrayList();

            for ( int i = 0; i < mealCategoriesCount; i += 1 ){

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");

                categoryNames.add(categoryName);

                Log.d(TAG, "readJsonFile: One of the categories is: ---------- " + categoryName);

            }

            // Method to pass the meals to the adapter
            Log.d(TAG, "readJsonFile: The array passed to the adapter is: --------- " + categoryNames );
            passToAdapter(categoryNames);


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

//    Implementation of method to pass the categories to the adapter
    public void passToAdapter(List<String> categoryNames){
        Log.d(TAG, "passToAdapter: init");

//        mainCategoryAdapter = new MainCategoryAdapter(categoryNames, this);

        wRecyclerView.setAdapter(mainCategoryAdapter);
        wRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        wRecyclerView.setHasFixedSize(true);
        mainCategoryAdapter.notifyDataSetChanged();
    }

}