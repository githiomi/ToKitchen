package org.githiomi.tokitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.githiomi.tokitchen.R;

public class ImageHolderFragment extends Fragment {

//    TAG
    private static final String TAG = ImageHolderFragment.class.getSimpleName();

    public ImageHolderFragment() {
        // Required empty public constructor
    }

    public static ImageHolderFragment newInstance() {
        ImageHolderFragment fragment = new ImageHolderFragment();
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
        return inflater.inflate(R.layout.fragment_image_holder, container, false);
    }
}