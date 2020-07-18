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
import org.githiomi.tokitchen.adapters.drinks.DrinksWithSizesAdapter;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.models.Drinks.DrinkCategoryTypesWithSizes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksWithSizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinksWithSizeFragment extends Fragment {

    // TAG
    private static final String TAG = DrinksWithSizeFragment.class.getSimpleName();

    // Widgets
    @BindView(R.id.drinksWithSizeRecyclerView) RecyclerView wDrinksWithSizeRecyclerView;

    // Local variables
    private String drinkTypeName;

    public DrinksWithSizeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DrinksWithSizeFragment newInstance(String drinkName) {
        DrinksWithSizeFragment fragment = new DrinksWithSizeFragment();
        Bundle args = new Bundle();
        args.putString(Constants.DRINK_TYPE_NAME, drinkName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinkTypeName = getArguments().getString(Constants.DRINK_TYPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_drinks_with_size, container, false);

        // binding views using butter knife
        ButterKnife.bind(this, mainView);

        // Method call to extract data from source.json
//        extractData(drinkTypeName);

        return mainView;
    }

    // Method implementation for the alt adapter for those with sizes
//    private void passToAdapter( List<DrinkCategoryTypesWithSizes> drinkCategoryTypesWithSizeToAdapter){
//        Log.d(TAG, "passToAdapter: drinksTypeSizesListToAdapter init");
//
//        Context context = getContext();
//
//        drinksWithSizesAdapter = new DrinksWithSizesAdapter(drinkCategoryTypesWithSizeToAdapter, context);
//
//        wDrinksWithSizeRecyclerView.setAdapter(drinksTypeAdapter);
//        wDrinksWithSizeRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//        wDrinksWithSizeRecyclerView.setHasFixedSize(true);
//        drinksTypeAdapter.notifyDataSetChanged();
//
//    }
}