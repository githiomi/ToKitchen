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

public class SandwichSpinner extends ArrayAdapter<String> {

    private String[] options;

    public SandwichSpinner(@NonNull Context context, String[] stringList) {
        super(context, 0, stringList);

        this.options = stringList;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sandwich_drop_down_item, parent, false);
        }

        TextView sizeName = (TextView) convertView.findViewById(R.id.sizeName);

        String option = options[position];

        if ( option != null ) {
            sizeName.setText(option);
        }

        return convertView;
    }

}
