package org.githiomi.tokitchen.fragments.breakfast;

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
import org.githiomi.tokitchen.adapters.breakfast.BreakfastAdapter;
import org.githiomi.tokitchen.models.Breakfast.Breakfast;
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

public class BreakfastFragment extends Fragment {

//    TAG
    private static final String TAG = BreakfastFragment.class.getSimpleName();

//    Widgets
    @BindView(R.id.breakfastRecyclerView) RecyclerView wBreakfastRecyclerView;

//    Local variables
    //For the adapter
    private BreakfastAdapter breakfastAdapter;
    // For the breakfast category name
    private String breakfastCategory;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the list of sub categories
    private List<DrinksCategory> drinksCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the breakfast simple objects
    private List<Breakfast> breakfastList;

    public BreakfastFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BreakfastFragment newInstance(String breakfastCategory) {
        BreakfastFragment fragment = new BreakfastFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BREAKFAST_TYPE_NAME, breakfastCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            breakfastCategory = getArguments().getString(Constants.BREAKFAST_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View breakfastFragmentView = inflater.inflate(R.layout.fragment_breakfast, container, false);

        // Binding widgets
        ButterKnife.bind(this, breakfastFragmentView);

        // Method call to extract data
        extractData(breakfastCategory);

        return breakfastFragmentView;
    }

    // Method implementation to extract data
    private void extractData(String breakfastCategory) {
        Log.d(TAG, "extractData: Breakfast fragment init");

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

                    if (subCategoryName.equals(breakfastCategory)) {

                        drinksCategoryList = new ArrayList();

                        // To get the meals in the sub category
                        String forTheDrinksInSubCategory = "meals";
                        JSONArray javaBreakfastArray = mealSubCategories.getJSONArray(forTheDrinksInSubCategory);

                        int lengthOfBreakfastCategory = javaBreakfastArray.length();

                        breakfastList = new ArrayList();
                        for (int z = 0; z < lengthOfBreakfastCategory; z += 1) {

                            JSONObject javaDrink = (JSONObject) javaBreakfastArray.get(z);

                            String toGetBreakfastName = "mealName";
                            String javaBreakfastName = javaDrink.getString(toGetBreakfastName);


                            String toGetBreakfastPrice = "mealPrice";
                            int javaBreakfastPrice = javaDrink.getInt(toGetBreakfastPrice);


                            // Creating the barista type objects
                            Breakfast breakfast = new Breakfast(javaBreakfastName, javaBreakfastPrice);
                            breakfastList.add(breakfast);

                            }
                    }
                }

                // Method to pass the meals to the adapter
                passToAdapter(breakfastList);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Method implementation to pass list to adapter
    private void passToAdapter(List<Breakfast> breakfastListToAdapter) {
        Log.d(TAG, "passToAdapter: breakfast to adapter init");

        Context context = getContext();

        breakfastAdapter = new BreakfastAdapter(breakfastListToAdapter, context);

        wBreakfastRecyclerView.setAdapter(breakfastAdapter);
        wBreakfastRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wBreakfastRecyclerView.setHasFixedSize(true);
        breakfastAdapter.notifyDataSetChanged();

    }
}