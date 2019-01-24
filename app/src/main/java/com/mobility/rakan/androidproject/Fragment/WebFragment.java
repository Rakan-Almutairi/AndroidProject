package com.mobility.rakan.androidproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobility.rakan.androidproject.R;

public class WebFragment extends Fragment {

    View rootView;

    TextView tvFOne;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.web_fragment, container, false);

        tvFOne = rootView.findViewById(R.id.wv_f_news);

        return rootView;
    }
}
