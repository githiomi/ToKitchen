package org.githiomi.tokitchen.adapters.drinks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.adapters.spinners.DrinkSizesSpinner;
import org.githiomi.tokitchen.models.Drinks.DrinkCategoryTypesWithSizes;
import org.githiomi.tokitchen.models.Drinks.DrinksTypeSizes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinksWithSizesAdapter extends RecyclerView.Adapter<DrinksWithSizesAdapter.DrinksWithSizesViewHolder> {

    // TAG
    private static final String TAG = DrinksWithSizesAdapter.class.getSimpleName();

    //Local variables
    // For the drink type name
    private String drinkTypeName;
    // For the list of drinks sizes
    private List<DrinkCategoryTypesWithSizes> drinkCategoryTypesWithSizesList;
    // For the context
    private Context context;

    public DrinksWithSizesAdapter(List<DrinkCategoryTypesWithSizes> drinkCategoryTypesWithSizesList, Context context) {
        this.drinkCategoryTypesWithSizesList = drinkCategoryTypesWithSizesList;
        this.context = context;
    }

    @NonNull
    @Override
    public DrinksWithSizesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_order_item, parent, false);
        DrinksWithSizesViewHolder drinksWithSizesViewHolder = new DrinksWithSizesViewHolder(mainView);
        return drinksWithSizesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksWithSizesViewHolder holder, int position) {
        holder.bindToView(drinkCategoryTypesWithSizesList.get(position));
    }

    @Override
    public int getItemCount() {
        return drinkCategoryTypesWithSizesList.size();
    }


    public class DrinksWithSizesViewHolder extends RecyclerView.ViewHolder
                                           implements View.OnClickListener {

        // Widgets
        @BindView(R.id.mealName) TextView wMealName;
        @BindView(R.id.ivButtonAddOrder) ImageView wButtonAddOrder;
        @BindView(R.id.spinnerSizeDropDown) Spinner wSizeSpinner;

        public DrinksWithSizesViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding widgets with butter knife
            ButterKnife.bind(this, itemView);

            // Adding an on click listener to the add to order image
            wButtonAddOrder.setOnClickListener(this);

        }

        // Custom method to bind the data to view
        private void bindToView(DrinkCategoryTypesWithSizes drinkCategoryTypeWithSize){

            wMealName.setText(drinkCategoryTypeWithSize.getDrinkCategoryTypeWithSizeName());

            List<DrinksTypeSizes> drinksTypeSizes = drinkCategoryTypeWithSize.getDrinksTypeSizesList();

            //Passing the list of sizes to the custom adapter
            DrinkSizesSpinner drinkSizesSpinner = new DrinkSizesSpinner(context, drinksTypeSizes);
            wSizeSpinner.setAdapter(drinkSizesSpinner);

        }

        // Method to implement the on click listener
        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wButtonAddOrder ){

                String baristaTypeName = drinkCategoryTypesWithSizesList.get(currentPosition).getDrinkCategoryTypeWithSizeName();
                Toast.makeText(context, "Order: " + baristaTypeName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
