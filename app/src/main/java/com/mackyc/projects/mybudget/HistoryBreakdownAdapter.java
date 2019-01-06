package com.mackyc.projects.mybudget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

import static com.mackyc.projects.mybudget.DashboardActivity.MODIFY_TRANSACTION_REQUEST;
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

        String itemCategoryStr;

        switch (item.getItemCategory()) {
            case CostBreakdownItem.CATEGORY_IMPORTANT:
                itemCategoryStr = "Important";
                break;
            case CostBreakdownItem.CATEGORY_UTILITIES:
                itemCategoryStr = "Utilities";
                break;
            case CostBreakdownItem.CATEGORY_FOOD:
                itemCategoryStr = "Food";
                break;
            case CostBreakdownItem.CATEGORY_TRANSPORTATION:
                itemCategoryStr = "Transportation";
                break;
            case CostBreakdownItem.CATEGORY_PERSONAL:
                itemCategoryStr = "Personal";
                break;
            case CostBreakdownItem.CATEGORY_ENTERTAINMENT:
                itemCategoryStr = "Entertainment";
                break;
            case CostBreakdownItem.CATEGORY_SUPPLIES:
                itemCategoryStr = "Supplies";
                break;
            default:
                itemCategoryStr = "Others";
        }

        holder.getItemCategory().setText(itemCategoryStr);
        holder.getItemCost().setText(String.format(Locale.getDefault(), "%s%.0f", currency, item.getItemCost()));

        if (DateUtils.isToday(item.getItemDateTime().getTime())) {

            DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
            String formattedTime = df.format(item.getItemDateTime());

            holder.getItemDateTime().setText(formattedTime);

        } else {

            SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            String formattedDate = sdf.format(item.getItemDateTime());

            holder.getItemDateTime().setText(formattedDate);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void clear() {
        historyBreakdownItems = new HistoryBreakdownItem[]{};
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<HistoryBreakdownItem> items) {
        mItems = items;
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

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), AddTransactionActivity.class);
                    i.putExtra("TxnName", mItems.get(getAdapterPosition()).getItemName());
                    i.putExtra("TxnCredit", mItems.get(getAdapterPosition()).isCredit());
                    i.putExtra("TxnCost", mItems.get(getAdapterPosition()).getItemCost());
                    i.putExtra("TxnCategory", mItems.get(getAdapterPosition()).getItemCategory());

                    i.putExtra("requestCode", MODIFY_TRANSACTION_REQUEST);
                    ((Activity) context).startActivityForResult(i, DashboardActivity.MODIFY_TRANSACTION_REQUEST);
                }
            });
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
