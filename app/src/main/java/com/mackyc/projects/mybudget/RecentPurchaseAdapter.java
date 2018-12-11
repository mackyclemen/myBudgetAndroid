package com.mackyc.projects.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecentPurchaseAdapter extends RecyclerView.Adapter<RecentPurchaseAdapter.MyViewHolder> {

    private final Context mContext;
    private RecentPurchaseList mItems[];

    public RecentPurchaseAdapter(Context context, RecentPurchaseList[] items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_recentpurchases, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getRecentItemName().setText(mItems[position].getItemName());
        holder.getRecentItemCost().setText(String.valueOf(mItems[position].getItemCost()));
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView recentItemName, recentItemCost;
        private final Context context;

        public MyViewHolder(View v) {
            super(v);
            recentItemName = v.findViewById(R.id.recentPurchaseName);
            recentItemCost = v.findViewById(R.id.recentPurchaseTender);
            context = v.getContext();
        }

        public TextView getRecentItemName() {
            return recentItemName;
        }

        public TextView getRecentItemCost() {
            return recentItemCost;
        }

        public Context getContext() {
            return context;
        }
    }

}
