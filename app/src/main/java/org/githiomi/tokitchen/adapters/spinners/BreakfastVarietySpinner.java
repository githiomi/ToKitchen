package org.githiomi.tokitchen.adapters.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Breakfast.BreakfastType;
import org.githiomi.tokitchen.models.Breakfast.BreakfastWT;
import org.githiomi.tokitchen.models.LunchAndDinner.QuickBiteTypes;

import java.util.List;

public class BreakfastVarietySpinner extends ArrayAdapter<BreakfastType> {

    public BreakfastVarietySpinner(@NonNull Context context, @NonNull List<BreakfastType> breakfastTypes) {
        super(context, 0, breakfastTypes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    // Custom method to assign views
    private View initView( int position, View convertView, ViewGroup parent){

        if ( convertView == null ){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_drop_down_item, parent, false);
        }

        TextView sizeName = (TextView) convertView.findViewById(R.id.sizeName);

        BreakfastType breakfastType = getItem(position);

        if ( breakfastType != null ) {
            sizeName.setText(breakfastType.getTypeName());
        }

        return convertView;
    }

}
