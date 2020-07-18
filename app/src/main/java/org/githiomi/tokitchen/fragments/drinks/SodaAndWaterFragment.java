package org.githiomi.tokitchen.fragments.drinks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.githiomi.tokitchen.R;

public class SodaAndWaterFragment extends Fragment {

    // TAG
    private static final String TAG = SodaAndWaterFragment.class.getSimpleName();


    public SodaAndWaterFragment() {
        // Required empty public constructor
    }

    public static SodaAndWaterFragment newInstance() {
        SodaAndWaterFragment fragment = new SodaAndWaterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soda_and_water, container, false);
    }
}