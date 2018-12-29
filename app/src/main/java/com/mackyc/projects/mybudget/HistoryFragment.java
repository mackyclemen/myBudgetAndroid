package com.mackyc.projects.mybudget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.mackyc.projects.mybudget.DashboardActivity.historyBreakdownItems;

public class HistoryFragment extends Fragment {

    RecyclerView historyRV;
    SwipeRefreshLayout historySwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_history, container, false);

        final HistoryBreakdownAdapter adapter = new HistoryBreakdownAdapter(getContext(), historyBreakdownItems);

        historyRV = v.findViewById(R.id.historyRV);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        historyRV.setLayoutManager(manager);
        historyRV.addItemDecoration(new DividerItemDecoration(historyRV.getContext(), DividerItemDecoration.VERTICAL));
        historyRV.setAdapter(adapter);

        historySwipeRefreshLayout = v.findViewById(R.id.historySwipeViewContainer);
        historySwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        historySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        adapter.addAll(historyBreakdownItems);
                        historySwipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        return v;
    }
}