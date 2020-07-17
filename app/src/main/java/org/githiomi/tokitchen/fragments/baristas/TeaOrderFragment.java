package org.githiomi.tokitchen.fragments.baristas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.githiomi.tokitchen.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeaOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeaOrderFragment extends Fragment {

//    TAG
    private static final String TAG = TeaOrderFragment.class.getSimpleName();

//    Local Variables

    public TeaOrderFragment() {
        // Required empty public constructor
    }

    public static TeaOrderFragment newInstance() {
        TeaOrderFragment fragment = new TeaOrderFragment();
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
        return inflater.inflate(R.layout.fragment_tea_order, container, false);
    }
}