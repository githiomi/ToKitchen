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
import org.githiomi.tokitchen.models.LunchAndDinner.Sandwiches;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SandwichesAdapter extends RecyclerView.Adapter<SandwichesAdapter.SandwichesAdapterVH> {

//    TAG
    private static final String TAG = SandwichesAdapter.class.getSimpleName();

//    Local variables
    private List<Sandwiches> sandwichesList;
    private Context context;

    public SandwichesAdapter(List<Sandwiches> sandwichesList, Context context) {
        this.sandwichesList = sandwichesList;
        this.context = context;
    }

    @NonNull
    @Override
    public SandwichesAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sandwiches_item, parent, false);
        SandwichesAdapterVH sandwichesAdapterVH = new SandwichesAdapterVH(adapterView);
        return sandwichesAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull SandwichesAdapterVH holder, int position) {
        holder.bindToView(sandwichesList.get(position));
    }

    @Override
    public int getItemCount() {
        return sandwichesList.size();
    }

    public class SandwichesAdapterVH extends RecyclerView.ViewHolder
                                     implements View.OnClickListener{

        // Adding widgets
        @BindView(R.id.sandwichName) TextView wSandwichName;
        @BindView(R.id.spinnerBreadDropDown) Spinner wSpinnerBreadDropDown;
        @BindView(R.id.spinnerAdditionsDropDown) Spinner wSpinnerAdditionsDropDown;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddOrder;

        public SandwichesAdapterVH(@NonNull View itemView) {
            super(itemView);

            // binding widgets
            ButterKnife.bind(this ,itemView);

            // On click listener
            wAddOrder.setOnClickListener(this);

        }

        // Method implementation to bind data to views
        private void bindToView(Sandwiches sandwich){

            wSandwichName.setText(sandwich.getSandwichName());

            String[] breadTypes = { "Brown Bread", "White Bread", "Baguette" };
            String[] additions = { "No additions", "Steamed Vegetables", "Green Salad", "Fruit Salad", "Chips" };

            SandwichSpinner sandwichBreadSpinner = new SandwichSpinner(context, breadTypes);
            wSpinnerBreadDropDown.setAdapter(sandwichBreadSpinner);

            SandwichSpinner sandwichAdditionsSpinner = new SandwichSpinner(context, additions);
            wSpinnerAdditionsDropDown.setAdapter(sandwichAdditionsSpinner);

        }

        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wAddOrder ){

                String sandwichName = sandwichesList.get(currentPosition).getSandwichName();
                Toast.makeText(context, sandwichName, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
