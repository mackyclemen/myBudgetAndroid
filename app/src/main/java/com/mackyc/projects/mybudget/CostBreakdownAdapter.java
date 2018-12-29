package com.mackyc.projects.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CostBreakdownAdapter extends RecyclerView.Adapter<CostBreakdownAdapter.CostBreakdownVH> {

    private Context mContext;
    private static CostBreakdownItem[] costBreakdownItems;

    public CostBreakdownAdapter(Context context, CostBreakdownItem[] items) {
        mContext = context;
        costBreakdownItems = items;
    }

    @NonNull
    @Override
    public CostBreakdownVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_breakdown, parent, false);

        return new CostBreakdownVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CostBreakdownVH holder, int position) {
        holder.getCategoryName().setText(costBreakdownItems[position].getCategoryName());
        holder.getCategoryTender().setText(String.valueOf(costBreakdownItems[position].getCategoryCost()));
    }

    @Override
    public int getItemCount() {
        return costBreakdownItems.length;
    }

    public static class CostBreakdownVH extends RecyclerView.ViewHolder {
        private final TextView categoryName, categoryTender;
        private final Context context;

        public CostBreakdownVH(View v) {
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

}
