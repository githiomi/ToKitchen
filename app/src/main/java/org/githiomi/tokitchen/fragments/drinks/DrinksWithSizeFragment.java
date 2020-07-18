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

import com.google.gson.JsonObject;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.drinks.DrinksWithSizesAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.Drinks.DrinkCategoryTypesWithSizes;
import org.githiomi.tokitchen.models.Drinks.DrinksCategory;
import org.githiomi.tokitchen.models.Drinks.DrinksCategoryTypes;
import org.githiomi.tokitchen.models.Drinks.DrinksTypeSizes;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DrinksWithSizeFragment extends Fragment {

    // TAG
    private static final String TAG = DrinksWithSizeFragment.class.getSimpleName();

    // Widgets
    @BindView(R.id.drinksWithSizeRecyclerView)
    RecyclerView wDrinksWithSizeRecyclerView;

    // Local variables
    // For the drink type passed down
    private String drinkTypeName;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the adapter
    private DrinksWithSizesAdapter drinksWithSizesAdapter;
    // For the list of the drinks category with the sizes
    private List<DrinkCategoryTypesWithSizes> drinkCategoryTypesWithSizesList;
    // For the list of the sizes for the category
    private List<DrinksTypeSizes> drinksTypeSizesList;

    public DrinksWithSizeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DrinksWithSizeFragment newInstance(String drinkName) {
        DrinksWithSizeFragment fragment = new DrinksWithSizeFragment();
        Bundle args = new Bundle();
        args.putString(Constants.DRINK_TYPE_NAME, drinkName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinkTypeName = getArguments().getString(Constants.DRINK_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_drinks_with_size, container, false);

        // binding views using butter knife
        ButterKnife.bind(this, mainView);

        // Method call to extract data from source.json
        extractData(drinkTypeName);

        return mainView;
    }

    // Method implementation to get data from the source.json file
    private void extractData(String drinkTypeName) {
        Log.d(TAG, "extractData: init");

        try {
//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(Constants.loadJsonFromAssets(getActivity()));

//            The key for the content I want to get
            String key = "java";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();

//            // init the lists
            categoryNames = new ArrayList();
            mainCategoryList = new ArrayList<>();

            for (int i = 0; i < mealCategoriesCount; i += 1) {

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");
                // Breakfast, Lunch & Dinner, Barista Corner, Drinks

                categoryNames.add(categoryName);

                // To get the categories inside the 4 main categories
                String mealCategoriesKey = "categories";
                JSONArray subCategories = mealCategory.getJSONArray(mealCategoriesKey);
                int length = subCategories.length();

                // Initializing the array list
                subCategoryList = new ArrayList();

                for (int d = 0; d < length; d += 1) {

                    JSONObject mealSubCategories = (JSONObject) subCategories.get(d);

                    String forTheSubCategories = "categoryName";
                    String subCategoryName = mealSubCategories.getString(forTheSubCategories);
                    Log.d(TAG, categoryName + " categories: " + subCategoryName);

                    if (subCategoryName.equals(drinkTypeName)) {

                        // To get the meals in the sub category
                        String forTheDrinksInSubCategory = "meals";
                        JSONArray javaDrinks = mealSubCategories.getJSONArray(forTheDrinksInSubCategory);

                        int lengthOfJavaDrinks = javaDrinks.length();

                        drinkCategoryTypesWithSizesList = new ArrayList();
                        for (int z = 0; z < lengthOfJavaDrinks; z += 1) {

                            JSONObject javaDrink = (JSONObject) javaDrinks.get(z);

                            String toGetDrinkName = "mealName";
                            String javaDrinkName = javaDrink.getString(toGetDrinkName);


                            String toGetDrinkSizes = "sizes";
                            JSONArray drinkSizes = javaDrink.getJSONArray(toGetDrinkSizes);

                            int drinkSizesLength = drinkSizes.length();

                            drinksTypeSizesList = new ArrayList();
                            for ( int s = 0; s < drinkSizesLength; s += 1) {

                                JSONObject singleDrinkSize = (JSONObject) drinkSizes.get(s);

                                String toGetTheSize = "sizeName";
                                String singleDrinkSizeName = singleDrinkSize.getString(toGetTheSize);

                                String toGetThePrice = "sizePrice";
                                int singleDrinkSizePrice = singleDrinkSize.getInt(toGetThePrice);


                                // Assigning the strings to a object of drink sizes
                                DrinksTypeSizes drinksTypeSize = new DrinksTypeSizes(singleDrinkSizeName, singleDrinkSizePrice);
                                drinksTypeSizesList.add(drinksTypeSize);
                            }

                            // Creating objects for the drinks with sizes
                            DrinkCategoryTypesWithSizes drinkCategoryTypesWithSize = new DrinkCategoryTypesWithSizes(javaDrinkName, drinksTypeSizesList);
                            drinkCategoryTypesWithSizesList.add(drinkCategoryTypesWithSize);

                        }

                        // Method to pass the meals to the adapter
                        passToAdapter(drinkCategoryTypesWithSizesList);

                    }


                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    // Method implementation for the alt adapter for those with sizes
    private void passToAdapter(List<DrinkCategoryTypesWithSizes> drinkCategoryTypesWithSizeToAdapter) {
        Log.d(TAG, "passToAdapter: drinksTypeSizesListToAdapter init");

        Context context = getContext();

        drinksWithSizesAdapter = new DrinksWithSizesAdapter(drinkCategoryTypesWithSizeToAdapter, context);

        wDrinksWithSizeRecyclerView.setAdapter(drinksWithSizesAdapter);
        wDrinksWithSizeRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wDrinksWithSizeRecyclerView.setHasFixedSize(true);
        drinksWithSizesAdapter.notifyDataSetChanged();

    }
}