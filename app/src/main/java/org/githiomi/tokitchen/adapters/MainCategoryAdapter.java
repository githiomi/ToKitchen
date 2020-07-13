package org.githiomi.tokitchen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Constants;
import org.githiomi.tokitchen.ui.SubCategoryActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder> {

//    TAG
    private static final String TAG = MainCategoryAdapter.class.getSimpleName();

//    Local Variables
    // The list of categories
    private List<String> mainCategories;
    // Context
    private Context context;

    public MainCategoryAdapter(List<String> mainCategories, Context context) {
        this.mainCategories = mainCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public MainCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_item, parent, false);
        MainCategoryViewHolder mainCategoryViewHolder = new MainCategoryViewHolder(mainView);
        return mainCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainCategoryViewHolder holder, int position) {
        holder.bind(mainCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mainCategories.size();
    }

    public class MainCategoryViewHolder extends RecyclerView.ViewHolder
                                        implements View.OnClickListener{

//        Widgets
        @BindView(R.id.categoryName) TextView wCategoryName;

        public MainCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            Binding widget with butter knife
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

//        Method that receives the item in the position and binds it to the views
        public void bind(String category){
            wCategoryName.setText(category);
        }

//        Method to be performed when each item is clicked
        @Override
        public void onClick(View view) {

            int itemPosition = getAdapterPosition();

            // Toast for the name
            String categoryName = mainCategories.get(itemPosition);
            Toast.makeText(context, categoryName + " category", Toast.LENGTH_SHORT).show();

            // This will take the category name and pass it
            Intent toSubCategory = new Intent(context, SubCategoryActivity.class);
            toSubCategory.putExtra(Constants.MAIN_CATEGORY_NAME, categoryName);
            context.startActivity(toSubCategory);

        }
    }
}
