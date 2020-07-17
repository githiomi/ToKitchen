package org.githiomi.tokitchen.fragments.baristas;

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
import org.githiomi.tokitchen.adapters.BaristaTypeAdapter;
import org.githiomi.tokitchen.models.Barista.BaristaCategory;
import org.githiomi.tokitchen.models.Barista.BaristaSizes;
import org.githiomi.tokitchen.models.Barista.BaristaType;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.MainCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeaOrderFragment extends Fragment {

    //    TAG
    private static final String TAG = TeaOrderFragment.class.getSimpleName();

    //    Widgets
    @BindView(R.id.teaRecyclerView)
    RecyclerView wTeaRecyclerView;

    //    Local Variables
    // For the adapter
    private BaristaTypeAdapter baristaTypeAdapter;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the list of sub categories
    private List<BaristaCategory> baristaSubCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of barista types
    private List<BaristaType> baristaTypeList;
    // For the list of barista sizes and prices
    private List<BaristaSizes> baristaSizesList;

    public TeaOrderFragment() {
        // Required empty public constructor
    }

    public static TeaOrderFragment newInstance() {
        TeaOrderFragment fragment = new TeaOrderFragment();
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
        View mainView = inflater.inflate(R.layout.fragment_tea_order, container, false);

        // Binding views
        ButterKnife.bind(this, mainView);

        // Method call to extract data
        extractData();

        return mainView;
    }

    //    Method to get data from the source.json file
    private void extractData() {
        Log.d(TAG, "extractData: init");

        // Actual call

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

                    if (bCategoryName.equals("Tea & Chocolate")) {

                        baristaSubCategoryList = new ArrayList();

                        // To get the meals in the sub category
                        String forTheMealInSubCategory = "meals";
                        JSONArray teaAndChocos = mealSubCategories.getJSONArray(forTheMealInSubCategory);

                        int lengthOfTeaAndChoco = teaAndChocos.length();

                        baristaTypeList = new ArrayList();
                        for (int z = 0; z < lengthOfTeaAndChoco; z += 1) {

                            JSONObject coffeeAndEspresso = (JSONObject) teaAndChocos.get(z);

                            String toGetTeaAndChoco = "mealName";
                            String teaAndChoco = coffeeAndEspresso.getString(toGetTeaAndChoco);

                            Log.d(TAG, "readJsonFile: Tea & Chocolate: --------------- " + teaAndChoco);


                            String forTheSizesOfTAC = "sizes";
                            JSONArray forTheSizesOfTACArray = coffeeAndEspresso.getJSONArray(forTheSizesOfTAC);

                            int sizeOfTAC = forTheSizesOfTACArray.length();

                            baristaSizesList = new ArrayList();
                            for (int x = 0; x < sizeOfTAC; x += 1) {

                                JSONObject theSizes = (JSONObject) forTheSizesOfTACArray.get(x);

                                String size = theSizes.getString("sizeName");
                                int sizePrice = theSizes.getInt("sizePrice");

                                // Creating a new barista sizes list
                                BaristaSizes baristaSize = new BaristaSizes(size, sizePrice);
                                baristaSizesList.add(baristaSize);

                                Log.d(TAG, "readJsonFile: One of the sizes for the " + teaAndChoco + " is: " + size + " that costs " + sizePrice);

                            }

                            // Creating the barista type objects
                            BaristaType baristaType = new BaristaType(teaAndChoco, baristaSizesList);
                            baristaTypeList.add(baristaType);

                        }
                    }
                }

                Log.d(TAG, "readJsonFile: One of the categories is: ---------- " + categoryName);


                // Method to pass the meals to the adapter
                Log.d(TAG, "readJsonFile: The array passed to the barista type adapter is: --------- " + baristaTypeList);
                passToAdapter(baristaTypeList);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //    Method that will pass the items
    private void passToAdapter(List<BaristaType> baristaTypeListForAdapter) {

        Log.d(TAG, "passToAdapter: init");

        Context context = getContext();

        baristaTypeAdapter = new BaristaTypeAdapter(baristaTypeListForAdapter, context);

        wTeaRecyclerView.setAdapter(baristaTypeAdapter);
        wTeaRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wTeaRecyclerView.setHasFixedSize(true);
        baristaTypeAdapter.notifyDataSetChanged();

    }
}