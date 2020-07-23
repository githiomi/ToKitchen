package org.githiomi.tokitchen.adapters.breakfast;

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
import org.githiomi.tokitchen.models.Breakfast.BreakfastWT;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreakfastWTAdapter extends RecyclerView.Adapter<BreakfastWTAdapter.ViewHolder> {

    // TAG
    private static final String TAG = BreakfastWTAdapter.class.getSimpleName();

    // Local variables
    // For the list of breakfast objects with types
    private List<BreakfastWT> breakfastWTList;
    // for the context
    private Context context;

    public BreakfastWTAdapter(List<BreakfastWT> breakfastWTList, Context context) {
        this.breakfastWTList = breakfastWTList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_type_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(adapterView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindToView(breakfastWTList.get(position));
    }

    @Override
    public int getItemCount() {
        return breakfastWTList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Widgets
        @BindView(R.id.breakfastName) TextView wBreakfastName;
        @BindView(R.id.spinnerVarietyDropDown) Spinner wVarietyDropDown;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddToOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding widgets
            ButterKnife.bind(this, itemView);

            // Setting on click listeners
            wAddToOrder.setOnClickListener(this);

        }

        // Custom method to bind data to views
        private void bindToView(BreakfastWT breakfastWT){

            wBreakfastName.setText(breakfastWT.getBreakfastName());

        }


        // Method implementation
        @Override
        public void onClick(View view) {

            int current = getAdapterPosition();

            if ( view == wAddToOrder ) {

                String mealName = breakfastWTList.get(current).getBreakfastName();
                Toast.makeText(context, mealName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
