package org.githiomi.tokitchen.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;

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

    public class MainCategoryViewHolder extends RecyclerView.ViewHolder {

//        Widgets
        @BindView(R.id.categoryName) TextView wCategoryName;

        public MainCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            Binding widget with butter knife
            ButterKnife.bind(this, itemView);
        }

        public void bind(String category){
            wCategoryName.setText(category);
        }
    }
}
