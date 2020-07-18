package org.githiomi.tokitchen.adapters.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Barista.BaristaSizes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerDropDownAdapter extends ArrayAdapter<BaristaSizes> {

    public SpinnerDropDownAdapter(Context context, List<BaristaSizes> baristaSizesList) {
        super(context, 0, baristaSizesList);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_drop_down_item, parent, false);
        }

        TextView sizeName = (TextView) convertView.findViewById(R.id.sizeName);
        TextView sizePrice = (TextView) convertView.findViewById(R.id.sizePrice);

        BaristaSizes baristaSize = getItem(position);

        if ( baristaSize != null ) {
            sizeName.setText(baristaSize.getBaristaSize());
            sizePrice.setText(baristaSize.toString());
        }

        return convertView;
    }
}
