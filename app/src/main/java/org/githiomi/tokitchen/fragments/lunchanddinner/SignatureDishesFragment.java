package org.githiomi.tokitchen.fragments.lunchanddinner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.githiomi.tokitchen.R;

public class SignatureDishesFragment extends Fragment {


    public SignatureDishesFragment() {
        // Required empty public constructor
    }

    public static SignatureDishesFragment newInstance() {
        SignatureDishesFragment fragment = new SignatureDishesFragment();
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
        return inflater.inflate(R.layout.fragment_signature_dishes, container, false);
    }
}