package com.mackyc.projects.mybudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    RecyclerView costBreakdown;
    RecyclerView recentPurchases;

    CostBreakdownList[] costBreakdownLists = {
            new CostBreakdownList("Food", 1080),
            new CostBreakdownList("Essentials", 360)
    };

    RecentPurchaseList[] recentPurchaseLists = {
            new RecentPurchaseList("NBS purchases", 300),
            new RecentPurchaseList("Ballpens", 60),
            new RecentPurchaseList("Shakeys Treat", 1080)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        costBreakdown = v.findViewById(R.id.costBreakdownRV);
        costBreakdown.setHasFixedSize(true);
        costBreakdown.setLayoutManager(new LinearLayoutManager(getContext()));
        costBreakdown.setAdapter(new CostBreakdownAdapter(getContext(), costBreakdownLists));

        recentPurchases = v.findViewById(R.id.recentPurchaseRV);
        recentPurchases.setHasFixedSize(true);
        recentPurchases.setLayoutManager(new LinearLayoutManager(getContext()));
        recentPurchases.setAdapter(new RecentPurchaseAdapter(getContext(), recentPurchaseLists));

        return v;
    }
}
