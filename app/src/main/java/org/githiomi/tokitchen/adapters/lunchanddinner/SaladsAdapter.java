package org.githiomi.tokitchen.adapters.lunchanddinner;

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
import org.githiomi.tokitchen.models.LunchAndDinner.Salads;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaladsAdapter extends RecyclerView.Adapter<SaladsAdapter.SaladsAdapterVH> {

//    TAG
    private static final String TAG = SaladsAdapter.class.getSimpleName();

//    Local variables
    // For the list of salads
    private List<Salads> saladsList;
    // For the context
    private Context context;

    public SaladsAdapter(List<Salads> saladsList, Context context) {
        this.saladsList = saladsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SaladsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_salad_item, parent, false);
        SaladsAdapterVH saladsAdapterVH = new SaladsAdapterVH(adapterView);
        return saladsAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull SaladsAdapterVH holder, int position) {
        holder.bindToView(saladsList.get(position));
    }

    @Override
    public int getItemCount() {
        return saladsList.size();
    }

    public class SaladsAdapterVH extends RecyclerView.ViewHolder
                                 implements View.OnClickListener{

        // Widgets
        @BindView(R.id.tvSaladName) TextView wSaladName;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddToOrder;

        public SaladsAdapterVH(@NonNull View itemView) {
            super(itemView);

            // Binding view using butter knife
            ButterKnife.bind(this, itemView);

            // Adding the on click listener
            itemView.setOnClickListener(this);

        }

        // Method to bind data to views
        private void bindToView(Salads salad){

            wSaladName.setText(salad.getSaladName());

            wAddToOrder.setOnClickListener(this);

        }


        // Method implementation for the on click listener
        @Override
        public void onClick(View view) {

            int currentSalad = getAdapterPosition();

            if ( view == wAddToOrder ){

                String saladName = saladsList.get(currentSalad).getSaladName();
                Toast.makeText(context, saladName, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
