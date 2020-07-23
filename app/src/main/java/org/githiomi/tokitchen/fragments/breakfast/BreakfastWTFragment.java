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
import org.githiomi.tokitchen.adapters.breakfast.BreakfastWTAdapter;
import org.githiomi.tokitchen.models.Breakfast.BreakfastType;
import org.githiomi.tokitchen.models.Breakfast.BreakfastWT;
import org.githiomi.tokitchen.models.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreakfastWTFragment extends Fragment {

    // TAG
    private static final String TAG = BreakfastWTFragment.class.getSimpleName();

    // Widgets
    @BindView(R.id.breakfastWTRecyclerView) RecyclerView wBreakfastWTRecyclerView;

    //Local variables
    // for the adapter
    private BreakfastWTAdapter breakfastWTAdapter;
    // For the passed in category name
    private String breakfastWTName;
    // For the list of breakfast with types
    private List<BreakfastWT> breakfastWTList;
    // For the list of types
    private List<BreakfastType> breakfastTypeList;

    public BreakfastWTFragment() {
        // Required empty public constructor
    }


    public static BreakfastWTFragment newInstance(String breakfastWTName) {
        BreakfastWTFragment fragment = new BreakfastWTFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BREAKFAST_TYPE_NAME, breakfastWTName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            breakfastWTName = getArguments().getString(Constants.BREAKFAST_TYPE_NAME);
            Log.d(TAG, "onCreate: BreakfastWT " + breakfastWTName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_breakfast_with_type, container, false);

        // Binding widgets
        ButterKnife.bind(this, fragmentView);

        // Method call to extract data
        extractData(breakfastWTName);

        return fragmentView;
    }

    // Method init to extract data
    private void extractData(String breakfastWTName){
        Log.d(TAG, "extractData: for the breakfast with types init");

        try {
//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(Constants.loadJsonFromAssets(getActivity()));

//            The key for the content I want to get
            String key = "java";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();

            for ( int i = 0; i < mealCategoriesCount; i += 1) {

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);

                String categoryName = mealCategory.getString("name");
                // Breakfast, Lunch & Dinner, Barista Corner, Drinks

                if (categoryName.equals("Breakfast")) {

                    String mealCategoriesKey = "categories";
                    JSONArray subCategories = mealCategory.getJSONArray(mealCategoriesKey);
                    int lengthOfSubCategories = subCategories.length();

                    for (int j = 0; j < lengthOfSubCategories; j += 1) {

                        JSONObject mealSubCategory = (JSONObject) subCategories.get(j);

                        String forTheSubCategories = "categoryName";
                        String subCategoryName = mealSubCategory.getString(forTheSubCategories);
                        Log.d(TAG, "extractData: category name: " + categoryName);

                        if (categoryName.equals(breakfastWTName)) {

                            // init list of objects
                            breakfastWTList = new ArrayList();

                            String toGetMealsInSubCategory = "meals";
                            JSONArray mealsInSubCategory = mealSubCategory.getJSONArray(toGetMealsInSubCategory);

                            int lengthOfMeals = mealSubCategory.length();

                            for (int k = 0; k < lengthOfMeals; k += 1) {

                                JSONObject meal = (JSONObject) mealsInSubCategory.get(k);

                                String toGetMealName = "mealName";
                                String mealName = meal.getString(toGetMealName);

                                String toGetMealTypes = "types";
                                JSONArray mealTypes = meal.getJSONArray(toGetMealTypes);

                                int lengthOfMealTypes = mealTypes.length();

                                // Type list init
                                breakfastTypeList = new ArrayList();

                                for (int l = 0; l < lengthOfMealTypes; l = +1) {

                                    // Single meal type
                                    JSONObject mealType = (JSONObject) mealTypes.get(l);

                                    String toGetTypeName = "typeName";
                                    String typeName = mealType.getString(toGetTypeName);

                                    String toGetTypePrice = "typePrice";
                                    int typePrice = mealType.getInt(toGetTypePrice);

                                    // Creating single objects for the types and adding them to list
                                    BreakfastType breakfastType = new BreakfastType(typeName, typePrice);
                                    breakfastTypeList.add(breakfastType);

                                }

                                // Creating objects for the single meal with its types and adding them to list
                                BreakfastWT breakfastWT = new BreakfastWT(mealName, breakfastTypeList);
                                breakfastWTList.add(breakfastWT);

                            }

                            // Passing this list to an adapter
                            passToAdapter(breakfastWTList);

                        }

                    }

                }
            }

        }
        catch (Exception exception){

            exception.printStackTrace();

        }

    }

    // Method implementation
    private void passToAdapter(List<BreakfastWT> breakfastWTListToAdapter) {
        Log.d(TAG, "passToAdapter: breakfast with type to adapter init");

        Context context = getContext();

        breakfastWTAdapter = new BreakfastWTAdapter(breakfastWTListToAdapter, context);

        wBreakfastWTRecyclerView.setAdapter(breakfastWTAdapter);
        wBreakfastWTRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wBreakfastWTRecyclerView.setHasFixedSize(true);
        breakfastWTAdapter.notifyDataSetChanged();

    }

}