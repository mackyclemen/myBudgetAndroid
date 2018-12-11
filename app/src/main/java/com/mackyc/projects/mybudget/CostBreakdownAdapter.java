package com.mackyc.projects.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CostBreakdownAdapter extends RecyclerView.Adapter<CostBreakdownAdapter.MyViewHolder> {

    private Context mContext;
    private static CostBreakdownList[] mItems;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_breakdown, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getCategoryName().setText(mItems[position].getCategoryName());
        holder.getCategoryTender().setText(String.valueOf(mItems[position].getCategoryCost()));
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName, categoryTender;
        private final Context context;

        public MyViewHolder(View v) {
            super(v);
            categoryName = v.findViewById(R.id.categoryName);
            categoryTender = v.findViewById(R.id.categoryTender);
            context = v.getContext();
        }

        public TextView getCategoryName() {
            return categoryName;
        }

        public TextView getCategoryTender() {
            return categoryTender;
        }

        public Context getContext() {
            return context;
        }
    }

    public CostBreakdownAdapter(Context context, CostBreakdownList[] items) {
        mContext = context;
        mItems = items;
    }

}
