package org.githiomi.tokitchen.adapters.baristas;

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
import org.githiomi.tokitchen.adapters.spinners.SpinnerDropDownAdapter;
import org.githiomi.tokitchen.models.Barista.BaristaSizes;
import org.githiomi.tokitchen.models.Barista.BaristaType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaristaTypeAdapter extends RecyclerView.Adapter<BaristaTypeAdapter.BaristaTypeViewHolder> {

//    TAG
    private static final String TAG = BaristaTypeAdapter.class.getSimpleName();

//    Local Variables
    // For the context
    private Context context;
    // For the list of barista types
    private List<BaristaType> baristaTypeList;

    public BaristaTypeAdapter(List<BaristaType> baristaTypeList, Context context) {
        this.context = context;
        this.baristaTypeList = baristaTypeList;
    }

    @NonNull
    @Override
    public BaristaTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_order_item, parent, false);
        BaristaTypeViewHolder baristaTypeViewHolder = new BaristaTypeViewHolder(mainView);
        return baristaTypeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaristaTypeViewHolder holder, int position) {
        holder.bindBaristaType(baristaTypeList.get(position));
    }

    @Override
    public int getItemCount() {
        return baristaTypeList.size();
    }

    public class BaristaTypeViewHolder extends RecyclerView.ViewHolder
                                       implements View.OnClickListener {

        // Widgets
        @BindView(R.id.mealName) TextView wMealName;
        @BindView(R.id.ivButtonAddOrder) ImageView wButtonAddOrder;
        @BindView(R.id.spinnerSizeDropDown) Spinner wSizeSpinner;

        public BaristaTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding views using butter knife
            ButterKnife.bind(this, itemView);

            // Setting an onclick listener for the item view
            wButtonAddOrder.setOnClickListener(this);
        }

        // Method for binding the views
        public void bindBaristaType(BaristaType baristaType){

            wMealName.setText(baristaType.getBaristaType());

            List<BaristaSizes> itemBaristaSizes = baristaType.getBaristaSizesList();

            //Passing the list of sizes to the custom adapter
            SpinnerDropDownAdapter spinnerDropDownAdapter = new SpinnerDropDownAdapter(context, itemBaristaSizes);
            wSizeSpinner.setAdapter(spinnerDropDownAdapter);

        }

        // Method for the item view on click listener
        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wButtonAddOrder ){

                String baristaTypeName = baristaTypeList.get(currentPosition).getBaristaType();
                Toast.makeText(context, "Order: " + baristaTypeName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
