package org.githiomi.tokitchen.adapters.drinks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Drinks.DrinksCategoryTypes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinksTypeAdapter extends RecyclerView.Adapter<DrinksTypeAdapter.DrinksTypeViewHolder> {

    // TAG
    private static final String TAG = DrinksTypeAdapter.class.getSimpleName();

    // Local Variables
    // For the context
    private Context context;
    // For the list of drink types
    private List<DrinksCategoryTypes> drinksCategoryTypesList;

    public DrinksTypeAdapter(List<DrinksCategoryTypes> drinksCategoryTypesList, Context context) {
        this.context = context;
        this.drinksCategoryTypesList = drinksCategoryTypesList;
    }

    @NonNull
    @Override
    public DrinksTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.soda_water_item, parent, false);
        DrinksTypeViewHolder drinksTypeViewHolder = new DrinksTypeViewHolder(mainView);
        return drinksTypeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksTypeViewHolder holder, int position) {
        holder.bindSodaAndWater(drinksCategoryTypesList.get(position));
    }

    @Override
    public int getItemCount() {
        return drinksCategoryTypesList.size();
    }

    public class DrinksTypeViewHolder extends RecyclerView.ViewHolder
                                      implements View.OnClickListener {

        // Widgets
        @BindView(R.id.tvSodaWaterName) TextView wSodaWaterName;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddOrder;

        public DrinksTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding widgets with butter knife
            ButterKnife.bind(this, itemView);

            // Setting an on click listener to the add button
            wAddOrder.setOnClickListener(this);

        }

        // Custom method to bind the item to the view
        private void bindSodaAndWater(DrinksCategoryTypes drinksCategoryType){

            // Name of the drink
            String drinkName = drinksCategoryType.getDrinkCategoryTypeName();
            wSodaWaterName.setText(drinkName);
        }

        // Implementation of the on click for the item view
        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wAddOrder ){

                String currentItemName = drinksCategoryTypesList.get(currentPosition).getDrinkCategoryTypeName();
                Toast.makeText(context, currentItemName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
