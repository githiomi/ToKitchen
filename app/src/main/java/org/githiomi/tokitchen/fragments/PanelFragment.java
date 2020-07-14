package org.githiomi.tokitchen.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.MainCategoryAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanelFragment extends Fragment {

//    TAG
    private static final String TAG = PanelFragment.class.getSimpleName();

//    Widgets
    // Recycler view
    @BindView(R.id.panelRecyclerView) RecyclerView panelRecyclerView;
    // The checkout button
    @BindView(R.id.cvCheckoutButton) CardView wToCheckoutButton;

//    Local variables
    // For the adapter
    private MainCategoryAdapter mainCategoryAdapter;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the images
    private int[] mainCategoryImages = {R.drawable.breakfast, R.drawable.lunchanddinner, R.drawable.barista, R.drawable.drinks};

    public PanelFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PanelFragment newInstance(String param1, String param2) {
        PanelFragment fragment = new PanelFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_panel, container, false);

        // Binding with butter knife
        ButterKnife.bind(this, fragmentView);

        // Method to get data from the json file
        readJsonFile();

        // Setting on click listener to the checkout button
        wToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Ready to checkout order", Toast.LENGTH_SHORT).show();
            }
        });

        return fragmentView;
    }

    //    Method to perform the call to read JSON data
    public void readJsonFile() {

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

            for ( int i = 0; i < mealCategoriesCount; i += 1 ){

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");

                categoryNames.add(categoryName);

                // To create the main category objects
                int mainCategoryImage = mainCategoryImages[i];

                // Create a new main category object
                mainCategoryList.add( new MainCategory(categoryName, mainCategoryImage) );

                Log.d(TAG, "readJsonFile: One of the categories is: ---------- " + categoryName);

            }

            // Method to pass the meals to the adapter
            Log.d(TAG, "readJsonFile: The array passed to the adapter is: --------- " + categoryNames );
            passToAdapter(mainCategoryList);


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    //    Implementation of method to pass the categories to the adapter
    public void passToAdapter(List<MainCategory> mainCategoryListToAdapter){
        Log.d(TAG, "passToAdapter: init");

        Context context = getContext();

        mainCategoryAdapter = new MainCategoryAdapter(mainCategoryListToAdapter, context);

        panelRecyclerView.setAdapter(mainCategoryAdapter);
        panelRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        panelRecyclerView.setHasFixedSize(true);
        mainCategoryAdapter.notifyDataSetChanged();
    }
}