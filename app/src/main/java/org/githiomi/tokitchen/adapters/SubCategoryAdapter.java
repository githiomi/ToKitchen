package org.githiomi.tokitchen.adapters;

import android.content.Context;
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

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

//    TAG
    private static final String TAG = SubCategoryAdapter.class.getSimpleName();

//    Local Variables
    // For the list
    private List<String> subCategoryNames;
    // For the context
    private Context context;

    public SubCategoryAdapter(List<String> subCategoryNames, Context context) {
        this.subCategoryNames = subCategoryNames;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_item, parent, false);
        SubCategoryViewHolder subCategoryViewHolder = new SubCategoryViewHolder(mainView);
        return subCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.bind(subCategoryNames.get(position));
    }

    @Override
    public int getItemCount() {
        return subCategoryNames.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

//        Variables
        // Widgets
        @BindView(R.id.categoryName) TextView wSubCategoryName;

        public SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding views with butter knife
            ButterKnife.bind(this, itemView );
        }

        public void bind(String subCategoryName){
            wSubCategoryName.setText(subCategoryName);
        }
    }
}
