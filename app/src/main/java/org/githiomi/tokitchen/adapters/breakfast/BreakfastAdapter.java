package org.githiomi.tokitchen.adapters.breakfast;

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
import org.githiomi.tokitchen.models.Breakfast.Breakfast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastAdapterVH> {

    // TAG
    private static final String TAG = BreakfastAdapter.class.getSimpleName();

    // Local variables
    private List<Breakfast> breakfastList;
    private Context context;

    public BreakfastAdapter(List<Breakfast> breakfastList, Context context) {
        this.breakfastList = breakfastList;
        this.context = context;
    }

    @NonNull
    @Override
    public BreakfastAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_breakfast_item, parent, false);
        BreakfastAdapterVH breakfastAdapterVH = new BreakfastAdapterVH(adapterView);
        return breakfastAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapterVH holder, int position) {
        holder.bindToView(breakfastList.get(position));
    }

    @Override
    public int getItemCount() {
        return breakfastList.size();
    }

    public class BreakfastAdapterVH extends RecyclerView.ViewHolder
                                    implements View.OnClickListener{

        // Widgets
        @BindView(R.id.tvBreakfastName) TextView wBreakfastName;
        @BindView(R.id.ivButtonAddOrder) ImageView wAddOrder;

        public BreakfastAdapterVH(@NonNull View itemView) {
            super(itemView);

            // Binding widgets
            ButterKnife.bind(this, itemView);

            // On click listener
            wAddOrder.setOnClickListener(this);

        }

        // Custom method to bind data to view
        private void bindToView(Breakfast breakfast){

            String breakfastMealName = breakfast.getMealName();
            wBreakfastName.setText(breakfastMealName);

        }

        @Override
        public void onClick(View view) {

            int currentPosition = getAdapterPosition();

            if ( view == wAddOrder ){

                String mealName = breakfastList.get(currentPosition).getMealName();
                Toast.makeText(context, mealName, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
