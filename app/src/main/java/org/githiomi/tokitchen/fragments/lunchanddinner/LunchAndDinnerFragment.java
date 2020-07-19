package org.githiomi.tokitchen.fragments.lunchanddinner;

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
import org.githiomi.tokitchen.adapters.lunchanddinner.LunchAndDinnerAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.Drinks.DrinksCategory;
import org.githiomi.tokitchen.models.Drinks.DrinksCategoryTypes;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBiteTypes;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBites;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunchAndDinnerFragment extends Fragment {

    //    TAG
    private static final String TAG = LunchAndDinnerFragment.class.getSimpleName();

    //    Widgets
    @BindView(R.id.lunchAndDinnerRecyclerView)
    RecyclerView wLunchAndDinnerRecyclerView;

    //    Local variables
    // For the adapter
    private LunchAndDinnerAdapter lunchAndDinnerAdapter;
    // For the quick bite passed into the fragment
    private String quickBite;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of quick bites
    private List<QuickBites> quickBitesList;
    // For the list types of quick bites
    private List<QuickBiteTypes> quickBiteTypesList;

    public LunchAndDinnerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LunchAndDinnerFragment newInstance(String quickBite) {
        LunchAndDinnerFragment fragment = new LunchAndDinnerFragment();
        Bundle args = new Bundle();
        args.putString(Constants.LUNCH_AND_DINNER_TYPE_NAME, quickBite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quickBite = getArguments().getString(Constants.LUNCH_AND_DINNER_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lunchAndDinnerFragmentView = inflater.inflate(R.layout.fragment_lunch_and_dinner, container, false);

        // Binding views using butter knife
        ButterKnife.bind(this, lunchAndDinnerFragmentView);

        // Method call to extract data from the source.json file
        extractData(quickBite);

        return lunchAndDinnerFragmentView;
    }

    // Method implementation to extract data from the source.json file
    private void extractData(String quickBiteExtract) {
        Log.d(TAG, "extractData: " + quickBiteExtract + " fragment init");

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

                    if (subCategoryName.equals(quickBiteExtract)) {

                        // To get the meals in the sub category
                        String forTheQuickBitesInSubCategory = "meals";
                        JSONArray javaQuickBites = mealSubCategories.getJSONArray(forTheQuickBitesInSubCategory);

                        int lengthOfQuickBites = javaQuickBites.length();

                        quickBitesList = new ArrayList();
                        for (int z = 0; z < lengthOfQuickBites; z += 1) {

                            JSONObject javaQuickBite = (JSONObject) javaQuickBites.get(z);

                            String toGetQuickBiteName = "mealName";
                            String quickBiteName = javaQuickBite.getString(toGetQuickBiteName);

                            String toGetQuickBiteTypes = "mealTypes";
                            JSONArray quickBiteTypes = javaQuickBite.getJSONArray(toGetQuickBiteTypes);

                            int lengthOfQuickBiteTypes = quickBiteTypes.length();

                            quickBiteTypesList = new ArrayList();
                            for ( int a = 0; a < lengthOfQuickBiteTypes; a += 1){

                                JSONObject quickBiteType = (JSONObject) quickBiteTypes.get(a);

                                String toGetTheTypeName = "typeName";
                                String quickBiteTypeName = quickBiteType.getString(toGetTheTypeName);

                                String toGetTheTypePrice = "typePrice";
                                int quickBiteTypePrice = quickBiteType.getInt(toGetTheTypePrice);

                                // Creating the objects for the types
                                QuickBiteTypes quickBiteTypesObject = new QuickBiteTypes(quickBiteTypeName, quickBiteTypePrice);
                                quickBiteTypesList.add(quickBiteTypesObject);

                            }

                            // Creating the quick bite objects
                            QuickBites quickBitesObject = new QuickBites(quickBiteName, quickBiteTypesList);
                            quickBitesList.add(quickBitesObject);

                        }

                        // Passing the data to the adapter
                        passToAdapter(quickBitesList);

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Method that will pass data to the adapter
    private void passToAdapter(List<QuickBites> quickBitesListToAdapter) {
        Log.d(TAG, "passToAdapter: " + quickBitesListToAdapter + " init");

        Context context = getContext();

        lunchAndDinnerAdapter = new LunchAndDinnerAdapter(quickBitesListToAdapter, context);

        wLunchAndDinnerRecyclerView.setAdapter(lunchAndDinnerAdapter);
        wLunchAndDinnerRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wLunchAndDinnerRecyclerView.setHasFixedSize(true);
        lunchAndDinnerAdapter.notifyDataSetChanged();

    }
}