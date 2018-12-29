package com.mackyc.projects.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.mackyc.projects.mybudget.DashboardActivity.currency;

public class HistoryBreakdownAdapter extends RecyclerView.Adapter<HistoryBreakdownAdapter.HistoryBreakdownVH> {

    private static ArrayList<HistoryBreakdownItem> mItems;
    private static HistoryBreakdownItem[] historyBreakdownItems;
    private Context context;

    public HistoryBreakdownAdapter(Context context, HistoryBreakdownItem[] items) {
        this.context = context;
        mItems = new ArrayList<>();
        mItems.addAll(Arrays.asList(items));
    }

    public HistoryBreakdownAdapter(Context context, ArrayList<HistoryBreakdownItem> items) {
        this.context = context;
        mItems = items;
    }

    @NonNull
    @Override
    public HistoryBreakdownVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_history, parent, false);

        return new HistoryBreakdownVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryBreakdownVH holder, int position) {
        HistoryBreakdownItem item = mItems.get(position);
        holder.getItemName().setText(item.getItemName());
        holder.getItemCategory().setText(item.getItemCategoryString());
        holder.getItemCost().setText(String.format(Locale.getDefault(), "%s%.0f", currency, item.getItemCost()));

        if (DateUtils.isToday(item.getItemDateTime().getTime())) {

            holder.getItemDateTime().setText(item.getItemTime());
            DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
            String formattedTime = df.format(item.getItemDateTime());

            holder.getItemDateTime().setText(formattedTime);

        } else {

            SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            sdf.applyPattern(sdf.toPattern().replaceAll("[@\\p{Alpha}]*y+[@^\\p{Alpha}]*", ""));
            String formattedDate = sdf.format(item.getItemDateTime());

            holder.getItemDateTime().setText(formattedDate);
        }
    }

    @Override
    public int getItemCount() {
        return historyBreakdownItems.length;
    }

    public void clear() {
        historyBreakdownItems = new HistoryBreakdownItem[]{};
        notifyDataSetChanged();
    }

    public void addEntry(HistoryBreakdownItem item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void removeEntry(int index) {
        mItems.remove(index);
        notifyDataSetChanged();
    }

    public void addAll(HistoryBreakdownItem[] items) {
        historyBreakdownItems = items;
        notifyDataSetChanged();
    }

    public static class HistoryBreakdownVH extends RecyclerView.ViewHolder {

        private final TextView itemName, itemCategory, itemCost, itemDateTime;
        private final Context context;


        public HistoryBreakdownVH(View v) {
            super(v);
            itemName = v.findViewById(R.id.historyName);
            itemCategory = v.findViewById(R.id.historyCategory);
            itemCost = v.findViewById(R.id.historyPrice);
            itemDateTime = v.findViewById(R.id.historyDate);
            context = v.getContext();
        }

        public TextView getItemName() {
            return itemName;
        }

        public TextView getItemCategory() {
            return itemCategory;
        }

        public TextView getItemCost() {
            return itemCost;
        }

        public TextView getItemDateTime() {
            return itemDateTime;
        }

        public Context getContext() {
            return context;
        }

    }
}
