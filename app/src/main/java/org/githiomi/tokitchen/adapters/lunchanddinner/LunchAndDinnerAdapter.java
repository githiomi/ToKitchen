package org.githiomi.tokitchen.adapters.lunchanddinner;

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
import org.githiomi.tokitchen.adapters.spinners.LunchAndDinnerSpinner;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBiteTypes;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBites;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunchAndDinnerAdapter extends RecyclerView.Adapter<LunchAndDinnerAdapter.LunchAndDinnerVH> {

//    TAG
    private static final String TAG = LunchAndDinnerAdapter.class.getSimpleName();

//    Local variables
    private List<QuickBites> quickBitesList;
    private Context context;

    public LunchAndDinnerAdapter(List<QuickBites> quickBitesList, Context context) {
        this.quickBitesList = quickBitesList;
        this.context = context;
    }

    @NonNull
    @Override
    public LunchAndDinnerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lunchAndDinnerAdapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_order_item, parent, false);
        LunchAndDinnerVH lunchAndDinnerVH = new LunchAndDinnerVH(lunchAndDinnerAdapterView);
        return lunchAndDinnerVH;
    }

    @Override
    public void onBindViewHolder(@NonNull LunchAndDinnerVH holder, int position) {
        holder.bindToView(quickBitesList.get(position));
    }

    @Override
    public int getItemCount() {
        return quickBitesList.size();
    }

    public class LunchAndDinnerVH extends RecyclerView.ViewHolder
                                  implements View.OnClickListener{

        // Widgets
        @BindView(R.id.mealName) TextView wMealName;
        @BindView(R.id.ivButtonAddOrder) ImageView wButtonAddOrder;
        @BindView(R.id.spinnerSizeDropDown) Spinner wSizeSpinner;

        public LunchAndDinnerVH(@NonNull View itemView) {
            super(itemView);

            // Binding view with butter knife
            ButterKnife.bind(this, itemView);

            // Setting the on click listener
            wButtonAddOrder.setOnClickListener(this);

        }

        // Custom method to bind data to views
        private void bindToView(QuickBites quickBite){

            wMealName.setText(quickBite.getMealName());

            List<QuickBiteTypes> quickBitesTypes = quickBite.getQuickBiteTypesList();

            //Passing the list of sizes to the custom adapter
            LunchAndDinnerSpinner lunchAndDinnerSpinner = new LunchAndDinnerSpinner(context, quickBitesTypes);
            wSizeSpinner.setAdapter(lunchAndDinnerSpinner);

        }

        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wButtonAddOrder ){

                String mealName = quickBitesList.get(currentPosition).getMealName();
                Toast.makeText(context, mealName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
