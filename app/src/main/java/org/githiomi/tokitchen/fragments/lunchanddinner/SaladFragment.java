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
import org.githiomi.tokitchen.adapters.lunchanddinner.SaladsAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBiteTypes;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBites;
import org.githiomi.tokitchen.models.LunchAndDinner.Salads;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaladFragment extends Fragment {

//    TAG
    private static final String TAG = SaladFragment.class.getSimpleName();

//    Widgets
    @BindView(R.id.saladRecyclerView) RecyclerView wSaladRecyclerView;

//    Local Variables
    // For the adapter
    private SaladsAdapter saladsAdapter;
    // For the quick bite passed into the fragment
    private String quickBite;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of salad objects
    private List<Salads> saladsList;

    public SaladFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SaladFragment newInstance() {
        SaladFragment fragment = new SaladFragment();
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
        View saladFragmentView = inflater.inflate(R.layout.fragment_salad, container, false);

        // Binding view with butter knife
        ButterKnife.bind(this, saladFragmentView);

        // Method call to extract data from the source.json file
        extractData();

        return saladFragmentView;

    }

    // Method implementation to extract data from the source.json file
    private void extractData() {
        Log.d(TAG, "extractData: Salad fragment init");

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

                    if (subCategoryName.equals("Salads")) {

                        // To get the meals in the sub category
                        String forTheSaladsCategory = "meals";
                        JSONArray javaSalads = mealSubCategories.getJSONArray(forTheSaladsCategory);

                        int lengthOfSalads = javaSalads.length();

                        saladsList = new ArrayList();
                        for (int z = 0; z < lengthOfSalads; z += 1) {

                            JSONObject javaSalad = (JSONObject) javaSalads.get(z);

                            String toGetSaladName = "mealName";
                            String saladName = javaSalad.getString(toGetSaladName);

                            String toGetSaladPrice = "mealPrice";
                            int saladPrice = javaSalad.getInt(toGetSaladPrice);


                            // Creating the quick bite objects
                            Salads salad = new Salads(saladName, saladPrice);
                            saladsList.add(salad);

                        }

                        // Passing the data to the adapter
                        passToAdapter(saladsList);

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Method implementation to pass the salads list to the adapter
    private void passToAdapter(List<Salads> saladsListToAdapter){
        Log.d(TAG, "passToAdapter: saladsListToAdapter init");

        Context context = getContext();
        saladsAdapter = new SaladsAdapter(saladsListToAdapter, context);

        wSaladRecyclerView.setAdapter(saladsAdapter);
        wSaladRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wSaladRecyclerView.setHasFixedSize(true);
        saladsAdapter.notifyDataSetChanged();

    }


}