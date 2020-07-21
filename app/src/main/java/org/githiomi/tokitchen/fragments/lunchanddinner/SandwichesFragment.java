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
import org.githiomi.tokitchen.adapters.lunchanddinner.SandwichesAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.LunchAndDinner.Salads;
import org.githiomi.tokitchen.models.LunchAndDinner.Sandwiches;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SandwichesFragment extends Fragment {

//    TAG
    private static final String TAG = SandwichesFragment.class.getSimpleName();

//    Widgets
    @BindView(R.id.sandwichesRecyclerView) RecyclerView wSandwichesRecyclerView;

//    Local variables
    // For the adapter
    private SandwichesAdapter sandwichesAdapter;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of sandwiches objects
    private List<Sandwiches> sandwichesList;

    public SandwichesFragment() {
        // Required empty public constructor
    }

    public static SandwichesFragment newInstance() {
        SandwichesFragment fragment = new SandwichesFragment();
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
        View sandwichesFragmentView = inflater.inflate(R.layout.fragment_sandwiches, container, false);

        // Binding widgets
        ButterKnife.bind(this, sandwichesFragmentView);

        // Method call to extract data
        extractData();

        return sandwichesFragmentView;
    }

    // Method implementation extract data
    private void extractData() {
        Log.d(TAG, "extractData: Sandwiches fragment init");

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

                    if (subCategoryName.equals("Sandwiches")) {

                        // To get the meals in the sub category
                        String forTheSandwichCategory = "meals";
                        JSONArray javaSandwiches = mealSubCategories.getJSONArray(forTheSandwichCategory);

                        int lengthOfSandwiches = javaSandwiches.length();

                        sandwichesList = new ArrayList();
                        for (int z = 0; z < lengthOfSandwiches; z += 1) {

                            JSONObject javaSandwich = (JSONObject) javaSandwiches.get(z);

                            String toGetSandwichName = "mealName";
                            String saladName = javaSandwich.getString(toGetSandwichName);

                            String toGetSandwichPrice = "mealPrice";
                            int saladPrice = javaSandwich.getInt(toGetSandwichPrice);

                            // Creating the quick bite objects
                            Sandwiches sandwich = new Sandwiches(saladName, saladPrice);
                            sandwichesList.add(sandwich);

                        }

                        // Passing the data to the adapter
                        passToAdapter(sandwichesList);

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    // Method implementation to pass data to adapter
    private void passToAdapter(List<Sandwiches> sandwichesListToAdapter) {
        Log.d(TAG, "passToAdapter: Sandwiches adapter init");

        Context context = getContext();
        sandwichesAdapter = new SandwichesAdapter(sandwichesListToAdapter, context);

        wSandwichesRecyclerView.setAdapter(sandwichesAdapter);
        wSandwichesRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wSandwichesRecyclerView.setHasFixedSize(true);
        sandwichesAdapter.notifyDataSetChanged();

    }
}