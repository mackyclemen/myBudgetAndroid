package com.mackyc.projects.mybudget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment { 

    // TODO: 12/11/2018 Add adapters for the recyclerviews
    // TODO: 12/11/2018 Add classes for the adapters
    // TODO: 12/11/2018 Add dummy items 

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
