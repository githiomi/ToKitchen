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
import org.githiomi.tokitchen.adapters.spinners.SandwichSpinner;
import org.githiomi.tokitchen.models.LunchAndDinner.Wraps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WrapsAdapter extends RecyclerView.Adapter<WrapsAdapter.WrapsAdapterVH> {

//    TAG
    private static final String TAG = WrapsAdapter.class.getSimpleName();

//    Local variables
    private List<Wraps> wrapsList;
    private Context context;

    public WrapsAdapter(List<Wraps> wrapsList, Context context) {
        this.wrapsList = wrapsList;
        this.context = context;
    }

    @NonNull
    @Override
    public WrapsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wrapsAdapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrap_item, parent, false);
        WrapsAdapterVH wrapsAdapterVH = new WrapsAdapterVH(wrapsAdapterView);
        return wrapsAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull WrapsAdapterVH holder, int position) {
        holder.bindToView(wrapsList.get(position));
    }

    @Override
    public int getItemCount() {
        return wrapsList.size();
    }

    public class WrapsAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Widgets
        @BindView(R.id.mealName) TextView wMealName;
        @BindView(R.id.spinnerServedWithDropDown) Spinner wSpinnerServedWithDropDown;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddOrder;

        public WrapsAdapterVH(@NonNull View itemView) {
            super(itemView);

            // Binding widgets
            ButterKnife.bind(this, itemView);

            // Adding on click listener
            wAddOrder.setOnClickListener(this);

        }

        // Method implementation to bind data to view
        private void bindToView(Wraps wrap){

            wMealName.setText(wrap.getWrapName());

            String[] additions = { "Steamed Vegetables", "Green Salad", "Fruit Salad", "Chips" };

            SandwichSpinner sandwichSpinner = new SandwichSpinner(context, additions);
            wSpinnerServedWithDropDown.setAdapter(sandwichSpinner);

        }

        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wAddOrder ){

                String wrapName = wrapsList.get(currentPosition).getWrapName();
                Toast.makeText(context, wrapName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
