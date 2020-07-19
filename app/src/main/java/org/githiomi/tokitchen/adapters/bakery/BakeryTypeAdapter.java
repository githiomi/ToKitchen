package org.githiomi.tokitchen.adapters.bakery;

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
import org.githiomi.tokitchen.models.Bakery.BakedGoods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BakeryTypeAdapter extends RecyclerView.Adapter<BakeryTypeAdapter.BakeryTypeAdapterVH> {

//    TAG
    private static final String TAG = BakeryTypeAdapter.class.getSimpleName();

//    Local variables
    // For the list of baked goods passed
    private List<BakedGoods> bakedGoodsList;
    // For the context
    private Context context;

    public BakeryTypeAdapter(List<BakedGoods> bakedGoodsList, Context context) {
        this.bakedGoodsList = bakedGoodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public BakeryTypeAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainAdapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_baked_good_item, parent, false);
        BakeryTypeAdapterVH bakeryTypeAdapterVH = new BakeryTypeAdapterVH(mainAdapterView);
        return bakeryTypeAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull BakeryTypeAdapterVH holder, int position) {
        holder.bindToView(bakedGoodsList.get(position));
    }

    @Override
    public int getItemCount() {
        return bakedGoodsList.size();
    }

    public class BakeryTypeAdapterVH extends RecyclerView.ViewHolder
                                     implements View.OnClickListener{

        // Widgets
        @BindView(R.id.tvBakedGoodName) TextView wBakedGoodName;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddOrder;

        public BakeryTypeAdapterVH(@NonNull View itemView) {
            super(itemView);

            // Binding views with butter knife
            ButterKnife.bind(this, itemView);

            // Adding click listener to the image view
            wAddOrder.setOnClickListener(this);

        }

        // Method implementation to bind the data to view
        private void bindToView(BakedGoods bakedGood){

            String bakedGoodName = bakedGood.getBakedGoodName();
            wBakedGoodName.setText(bakedGoodName);

        }


        // Method implementation when the plus is clicked
        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wAddOrder ){

                String bakedGoodName = bakedGoodsList.get(currentPosition).getBakedGoodName();
                Toast.makeText(context, bakedGoodName, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
