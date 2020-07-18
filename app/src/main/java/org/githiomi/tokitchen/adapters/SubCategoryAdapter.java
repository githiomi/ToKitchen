package org.githiomi.tokitchen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.fragments.baristas.CoffeeOrderFragment;
import org.githiomi.tokitchen.fragments.ImageHolderFragment;
import org.githiomi.tokitchen.fragments.baristas.TeaOrderFragment;
import org.githiomi.tokitchen.fragments.drinks.DrinksFragment;
import org.githiomi.tokitchen.fragments.drinks.DrinksWithSizeFragment;

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
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item, parent, false);
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

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder
                                       implements View.OnClickListener{

//        Variables
        // Widgets
        @BindView(R.id.categoryName) TextView wSubCategoryName;

        public SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding views with butter knife
            ButterKnife.bind(this, itemView );

            // Setting an on click listener
            itemView.setOnClickListener(this);
        }

        public void bind(String subCategoryName){
            wSubCategoryName.setText(subCategoryName);
        }

        @Override
        public void onClick(View view) {
            int currentItem = getAdapterPosition();

            String currentSubCategory = subCategoryNames.get(currentItem);
            Toast.makeText(context, currentSubCategory, Toast.LENGTH_SHORT).show();

            if ( currentSubCategory.equals("Coffee & Espresso") ){

                CoffeeOrderFragment coffeeOrderFragment = CoffeeOrderFragment.newInstance();
                toReplace(coffeeOrderFragment);

            }
            else if ( currentSubCategory.equals("Tea & Chocolate") ){

                TeaOrderFragment teaOrderFragment = TeaOrderFragment.newInstance();
                toReplace(teaOrderFragment);

            }
            else if ( currentSubCategory.equals("Soda & Water") ){

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }

            else if ( currentSubCategory.equals("Java Shakes") ){

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }

            else if ( currentSubCategory.equals("Super Smoothies") ){

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }

            else if ( currentSubCategory.equals("Iced Tea") ) {

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }
            else if ( currentSubCategory.equals("Iced Coffee") ) {

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }
            else if ( currentSubCategory.equals("Fresh Juices") ) {

                DrinksWithSizeFragment drinksWithSizeFragment = DrinksWithSizeFragment.newInstance(currentSubCategory);
                toReplace(drinksWithSizeFragment);

            }
            else if ( currentSubCategory.equals("Freshly Squeezed Lemonades") ) {

                DrinksWithSizeFragment drinksWithSizeFragment = DrinksWithSizeFragment.newInstance(currentSubCategory);
                toReplace(drinksWithSizeFragment);

            }
            else if ( currentSubCategory.equals("Coladas") ) {

                DrinksFragment drinksFragment = DrinksFragment.newInstance(currentSubCategory);
                toReplace(drinksFragment);

            }
            else {

                ImageHolderFragment imageHolderFragment = ImageHolderFragment.newInstance();
                toReplace(imageHolderFragment);

            }

        }
    }

    // Custom method that will take in a fragment and use it to replace a view
    private void toReplace(Fragment fragment){

        FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mealSelectFragment, fragment);
        ft.commit();

    }
}
