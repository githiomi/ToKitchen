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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinksFragment extends Fragment {

    // TAG
    private static final String TAG = DrinksFragment.class.getSimpleName();

    // Widget
    @BindView(R.id.drinksRecyclerView)
    RecyclerView wDrinksRecyclerView;

    // Local Variables
    // For the type of drink passed in
    private String drinksType;
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

    public DrinksFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DrinksFragment newInstance(String drinkTypeName) {
        DrinksFragment fragment = new DrinksFragment();
        Bundle args = new Bundle();
        args.putString(Constants.DRINK_TYPE_NAME, drinkTypeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinksType = getArguments().getString(Constants.DRINK_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_drinks, container, false);

        // Binding widgets
        ButterKnife.bind(this, mainView);

        // Method call to extract data from source.json
        extractData(drinksType);

        return mainView;
    }

    // Method implementation for the extraction of data from source.json
    private void extractData(String drinkType) {
        Log.d(TAG, "extractData: " + drinkType + " fragment init");

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
                JSONArray subCategories = mealCategory.getJSONArray(mealCategoriesKey);
                int length = subCategories.length();

                // Initializing the array list
                subCategoryList = new ArrayList();

                for (int d = 0; d < length; d += 1) {

                    JSONObject mealSubCategories = (JSONObject) subCategories.get(d);

                    String forTheSubCategories = "categoryName";
                    String subCategoryName = mealSubCategories.getString(forTheSubCategories);
                    Log.d(TAG, categoryName + " categories: " + subCategoryName);

                    if (subCategoryName.equals(drinkType)) {

                        drinksCategoryList = new ArrayList();

                        // To get the meals in the sub category
                        String forTheDrinksInSubCategory = "meals";
                        JSONArray javaDrinks = mealSubCategories.getJSONArray(forTheDrinksInSubCategory);

                        if ( javaDrinks != null ) {

                            int lengthOfJavaDrinks = javaDrinks.length();

                            drinksCategoryTypesList = new ArrayList();
                            for (int z = 0; z < lengthOfJavaDrinks; z += 1) {

                                JSONObject javaDrink = (JSONObject) javaDrinks.get(z);

                                String toGetDrinkName = "mealName";
                                String javaDrinkName = javaDrink.getString(toGetDrinkName);


                                String toGetDrinkPrice = "mealPrice";
                                int javaDrinkPrice = javaDrink.getInt(toGetDrinkPrice);

                                Log.d(TAG, "readJsonFile: Soda And Water: --------------- " + javaDrinkName + " that will cost: Ksh." + javaDrinkPrice);

                                // Creating the barista type objects
                                DrinksCategoryTypes drinksCategoryType = new DrinksCategoryTypes(javaDrinkName, javaDrinkPrice);
                                drinksCategoryTypesList.add(drinksCategoryType);

                            }
                        }

                        // Creating the drinks category list that holds soda and water and its meals
                        DrinksCategory drinksCategory = new DrinksCategory(subCategoryName, drinksCategoryTypesList);
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

    // Method implementation of the function that will pass data to the adapter
    private void passToAdapter(List<DrinksCategoryTypes> drinksCategoryTypesToAdapter){
        Log.d(TAG, "passToAdapter: drinksCategoryTypesListToAdapter init");

        Context context = getContext();

        drinksTypeAdapter = new DrinksTypeAdapter(drinksCategoryTypesToAdapter, context);

        wDrinksRecyclerView.setAdapter(drinksTypeAdapter);
        wDrinksRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wDrinksRecyclerView.setHasFixedSize(true);
        drinksTypeAdapter.notifyDataSetChanged();

    }
}