package org.githiomi.tokitchen.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.MainCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder> {

//    TAG
    private static final String TAG = MainCategoryAdapter.class.getSimpleName();

//    Local Variables
    // The list of categories
    private List<MainCategory> mainCategories;
    // Context
    private Context context;
    // For the sub category adapter
    private SubCategoryAdapter subCategoryAdapter;

    public MainCategoryAdapter(List<MainCategory> mainCategories, Context context) {
        this.mainCategories = mainCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public MainCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_item, parent, false);
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
        @BindView(R.id.tvMainCategory) TextView wCategoryName;
        @BindView(R.id.ivMainCategory) ImageView wCategoryImage;
        @BindView(R.id.arrowImage) ImageView wArrowImage;
        @BindView(R.id.subCategoryRecyclerView) RecyclerView wSubCategoryRecyclerView;

        public MainCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            Binding widget with butter knife
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

//        Method that receives the item in the position and binds it to the views
        public void bind(MainCategory mainCategory){

            Picasso.get().load(mainCategory.getMainCategoryImage())
                    .into(wCategoryImage);

            wCategoryName.setText(mainCategory.getMainCategoryName());

            // The adapter for the sub categories
            subCategoryAdapter = new SubCategoryAdapter(mainCategory.getSubCategoryNames(), context);

            wSubCategoryRecyclerView.setAdapter(subCategoryAdapter);
            wSubCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(context));

            wSubCategoryRecyclerView.setHasFixedSize(true);
            subCategoryAdapter.notifyDataSetChanged();

            // To customize display depending on the state of isExpanded()
            boolean isExpanded = mainCategory.isExpanded();

            if (isExpanded) {
                dropDown();
            } else {
                backUp();
            }
        }

//        Method to be performed when each item is clicked
        @Override
        public void onClick(View view) {

            int itemPosition = getAdapterPosition();

            // To change the is expanded property
            MainCategory mainCategoryItem = mainCategories.get(itemPosition);
            mainCategoryItem.setExpanded( !(mainCategoryItem.isExpanded()) );
            notifyItemChanged(itemPosition);

        }

        // Methods to hide and show views
        public void dropDown() {
            // To show
            TransitionManager.beginDelayedTransition(wSubCategoryRecyclerView, new AutoTransition());
            wArrowImage.setImageResource(R.drawable.ic_arrow_up);
            wSubCategoryRecyclerView.setVisibility(View.VISIBLE);
        }

        public void backUp() {
            // To hide
            TransitionManager.beginDelayedTransition(wSubCategoryRecyclerView, new AutoTransition());
            wArrowImage.setImageResource(R.drawable.ic_arrow_down);
            wSubCategoryRecyclerView.setVisibility(View.GONE);
        }
    }
}
