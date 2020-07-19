package org.githiomi.tokitchen.fragments.bakery;

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
import org.githiomi.tokitchen.adapters.bakery.BakeryTypeAdapter;
import org.githiomi.tokitchen.adapters.drinks.DrinksTypeAdapter;
import org.githiomi.tokitchen.models.Bakery.BakedGoods;
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

public class BakeryFragment extends Fragment {

    //    TAG
    private static final String TAG = BakeryFragment.class.getSimpleName();

    //    Widgets
    @BindView(R.id.bakeryRecyclerView)
    RecyclerView wBakeryRecyclerView;

    //    Local variables
    // For the adapter
    private BakeryTypeAdapter bakeryTypeAdapter;
    // For the string passed in (bakery sub category)
    private String bakerySubCategory;
    // For the main categories
    private List<String> categoryNames;
    // For the list of main category objects
    private List<MainCategory> mainCategoryList;
    // For the other list
    private List<String> subCategoryList;
    // For the list of baked goods
    private List<BakedGoods> bakedGoodsList;

    public BakeryFragment() {
        // Required empty public constructor
    }

    public static BakeryFragment newInstance(String bakerySubCategory) {
        BakeryFragment fragment = new BakeryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BAKERY_TYPE_NAME, bakerySubCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bakerySubCategory = getArguments().getString(Constants.BAKERY_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_bakery, container, false);

        // Binding views using butter knife
        ButterKnife.bind(this, mainView);

        // Method call to get data from the source.json file
        extractData(bakerySubCategory);

        return mainView;
    }

    // Method implementation to get data from the source.json file
    private void extractData(String bakerySubCategory) {
        Log.d(TAG, "extractData: " + bakerySubCategory + " fragment init");

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

                    if (subCategoryName.equals(bakerySubCategory)) {

                        bakedGoodsList = new ArrayList();

                        // To get the meals in the sub category
                        String forTheDrinksInSubCategory = "meals";
                        JSONArray javaDrinks = mealSubCategories.getJSONArray(forTheDrinksInSubCategory);


                        int lengthOfJavaDrinks = javaDrinks.length();

                        for (int z = 0; z < lengthOfJavaDrinks; z += 1) {

                            JSONObject javaDrink = (JSONObject) javaDrinks.get(z);

                            String toGetBakedGoodName = "mealName";
                            String bakedGoodName = javaDrink.getString(toGetBakedGoodName);


                            String toGetBakedGoodPrice = "mealPrice";
                            int bakedGoodPrice = javaDrink.getInt(toGetBakedGoodPrice);

                            Log.d(TAG, "readJsonFile: Baked Good: --------------- " + bakedGoodName + " that will cost: Ksh." + bakedGoodPrice);

                            // Creating the bakery type objects
                            BakedGoods bakedGood = new BakedGoods(bakedGoodName, bakedGoodPrice);
                            bakedGoodsList.add(bakedGood);

                        }
                    }
                }

                // Method to pass the meals to the adapter
                Log.d(TAG, "readJsonFile: The array passed to the barista type adapter is: --------- " + bakedGoodsList);
                passToAdapter(bakedGoodsList);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // This is the method implementation that will get the list to the adapter
    private void passToAdapter(List<BakedGoods> bakedGoodsListToAdapter) {
        Log.d(TAG, "passToAdapter: bakedGoodsListToAdapter init");

        Context context = getContext();

        bakeryTypeAdapter = new BakeryTypeAdapter(bakedGoodsListToAdapter, context);

        wBakeryRecyclerView.setAdapter(bakeryTypeAdapter);
        wBakeryRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        wBakeryRecyclerView.setNestedScrollingEnabled(false);
        bakeryTypeAdapter.notifyDataSetChanged();

    }
}