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
import org.githiomi.tokitchen.adapters.lunchanddinner.WrapsAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.LunchAndDinner.Salads;
import org.githiomi.tokitchen.models.LunchAndDinner.Wraps;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WrapsFragment extends Fragment {

//    TAG
    private static final String TAG = WrapsFragment.class.getSimpleName();

//    Widgets
    @BindView(R.id.wrapsRecyclerView) RecyclerView wWrapsRecyclerView;

//    Local variables
    // For the adapter
    private WrapsAdapter wrapsAdapter;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of wraps
    private List<Wraps> wrapsList;

    public WrapsFragment() {
        // Required empty public constructor
    }


    public static WrapsFragment newInstance() {
        WrapsFragment fragment = new WrapsFragment();
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
        View wrapsFragmentView = inflater.inflate(R.layout.fragment_wraps, container, false);

        // Binding the widgets
        ButterKnife.bind(this, wrapsFragmentView);

        // Method call to extract data
        extractData();

        return wrapsFragmentView;
    }

//    Method implementation to extract data
    private void extractData() {
        Log.d(TAG, "extractData: Wraps fragment init");

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

                    if (subCategoryName.equals("Wraps")) {

                        // To get the meals in the sub category
                        String forTheWrapsCategory = "meals";
                        JSONArray javaWraps = mealSubCategories.getJSONArray(forTheWrapsCategory);

                        int lengthOfWraps = javaWraps.length();

                        wrapsList = new ArrayList();
                        for (int z = 0; z < lengthOfWraps; z += 1) {

                            JSONObject javaWrap = (JSONObject) javaWraps.get(z);

                            String toGetWrapName = "mealName";
                            String wrapName = javaWrap.getString(toGetWrapName);

                            String toGetWrapPrice = "mealPrice";
                            int wrapPrice = javaWrap.getInt(toGetWrapPrice);


                            // Creating the quick bite objects
                            Wraps wrap = new Wraps(wrapName, wrapPrice);
                            wrapsList.add(wrap);

                        }

                        // Passing the data to the adapter
                        passToAdapter(wrapsList);

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    // Method implementation to pass data to adapter
    private void passToAdapter(List<Wraps> wrapsListToAdapter) {
        Log.d(TAG, "passToAdapter: Wraps List Adapter init");

        Context context = getContext();

        wrapsAdapter = new WrapsAdapter(wrapsListToAdapter, context);

        wWrapsRecyclerView.setAdapter(wrapsAdapter);
        wWrapsRecyclerView.setLayoutManager( new LinearLayoutManager(context) );

        wWrapsRecyclerView.setHasFixedSize(true);
        wrapsAdapter.notifyDataSetChanged();
    }
}