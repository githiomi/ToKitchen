package org.githiomi.tokitchen.fragments.drinks;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.DrinksTypeAdapter;
import org.githiomi.tokitchen.models.Barista.BaristaCategory;
import org.githiomi.tokitchen.models.Barista.BaristaSizes;
import org.githiomi.tokitchen.models.Barista.BaristaType;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.Drinks.DrinksCategory;
import org.githiomi.tokitchen.models.Drinks.DrinksCategoryTypes;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SodaAndWaterFragment extends Fragment {

// TAG
    private static final String TAG = SodaAndWaterFragment.class.getSimpleName();

// Local Variables
    // For the adapter
    private DrinksTypeAdapter drinksTypeAdapter;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the list of sub categories
    private List<DrinksCategory> drinksCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of barista types
    private List<DrinksCategoryTypes> drinksCategoryTypesList;

//    Widgets
    @BindView(R.id.sodaAndWaterRecyclerView) RecyclerView wSodaAndWaterRecyclerView;

    public SodaAndWaterFragment() {
        // Required empty public constructor
    }

    public static SodaAndWaterFragment newInstance() {
        SodaAndWaterFragment fragment = new SodaAndWaterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_soda_and_water, container, false);

        // Binding with butter knife
        ButterKnife.bind(this, mainView);

        // Method call to get data from the source.json file
        extractData();

        return mainView;
    }

//    The method implementation for extracting data
    private void extractData(){
        Log.d(TAG, "extractData: init");

        try {
//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(Constants.loadJsonFromAssets(getActivity()));

//            The key for the content I want to get
            String key = "java";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();

            // init the lists
            categoryNames = new ArrayList();
            mainCategoryList = new ArrayList<>();

            for (int i = 0; i < mealCategoriesCount; i += 1) {

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");
                // Breakfast, Lunch & Dinner, Barista Corner, Drinks

                categoryNames.add(categoryName);

                // To get the categories inside the 4 main categories
                String mealCategoriesKey = "categories";
                JSONArray breakfastCategories = mealCategory.getJSONArray(mealCategoriesKey);
                int length = breakfastCategories.length();

                // Initializing the array list
                subCategoryList = new ArrayList();

                for (int d = 0; d < length; d += 1) {

                    JSONObject mealSubCategories = (JSONObject) breakfastCategories.get(d);

                    String forTheSubCategories = "categoryName";
                    String bCategoryName = mealSubCategories.getString(forTheSubCategories);
                    Log.d(TAG, categoryName + " categories: " + bCategoryName);

                    if (bCategoryName.equals("Soda & Water")) {

                        drinksCategoryList = new ArrayList();

                        // To get the meals in the sub category
                        String forTheMealsInSubCategory = "meals";
                        JSONArray sodAndWater = mealSubCategories.getJSONArray(forTheMealsInSubCategory);

                        int lengthOfCoffeeAndEsp = sodAndWater.length();

                        drinksCategoryTypesList = new ArrayList();
                        for (int z = 0; z < lengthOfCoffeeAndEsp; z += 1) {

                            JSONObject sodaAndWater = (JSONObject) sodAndWater.get(z);

                            String toGetSodaAndWaterName = "mealName";
                            String sodaAndWaterName = sodaAndWater.getString(toGetSodaAndWaterName);


                            String toGetSodaAndWaterPrice = "mealPrice";
                            int sodaAndWaterPrice = sodaAndWater.getInt(toGetSodaAndWaterPrice);

                            Log.d(TAG, "readJsonFile: Soda And Water: --------------- " + sodaAndWaterName + " that will cost: Ksh." + sodaAndWaterPrice );

                            // Creating the barista type objects
                            DrinksCategoryTypes drinksCategoryType = new DrinksCategoryTypes(sodaAndWaterName, sodaAndWaterPrice);
                            drinksCategoryTypesList.add(drinksCategoryType);

                        }

                        // Creating the drinks category list that holds soda and water and its meals
                        DrinksCategory drinksCategory = new DrinksCategory(bCategoryName, drinksCategoryTypesList);
                        drinksCategoryList.add(drinksCategory);

                    }



                }

                Log.d(TAG, "readJsonFile: One of the categories is: ---------- " + categoryName);


                // Method to pass the meals to the adapter
                Log.d(TAG, "readJsonFile: The array passed to the barista type adapter is: --------- " + drinksCategoryTypesList);
                passToAdapter(drinksCategoryTypesList);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    Method implementation to pass the types of soda and water to the adapter
    private void passToAdapter(List<DrinksCategoryTypes> drinksCategoryTypesListToAdapter){
        Log.d(TAG, "passToAdapter: drinksCategoryTypesListToAdapter init");

        Context context = getContext();

        drinksTypeAdapter = new DrinksTypeAdapter(drinksCategoryTypesListToAdapter, context);

        wSodaAndWaterRecyclerView.setAdapter(drinksTypeAdapter);
        wSodaAndWaterRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wSodaAndWaterRecyclerView.setHasFixedSize(true);
        drinksTypeAdapter.notifyDataSetChanged();

    }
}