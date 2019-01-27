package com.mackyc.projects.mybudget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static com.mackyc.projects.mybudget.DashboardActivity.MODIFY_TRANSACTION_REQUEST;
import static com.mackyc.projects.mybudget.DashboardActivity.currency;

public class ItemInvoiceAdapter extends RecyclerView.Adapter<ItemInvoiceAdapter.ItemInvoiceVH> {

    private static ArrayList<ItemInvoice> mItems = new ArrayList<>();
    private Context context;

    public ItemInvoiceAdapter(Context context, ItemInvoice[] items) {
        this.context = context;
        mItems.addAll(Arrays.asList(items));
    }

    public ItemInvoiceAdapter(Context context, ArrayList<ItemInvoice> items) {
        this.context = context;
        mItems = items;
    }

    @NonNull
    @Override
    public ItemInvoiceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_history, parent, false);

        return new ItemInvoiceVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemInvoiceVH holder, int position) {
        ItemInvoice item = mItems.get(position);
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
        holder.getItemCost().setText(String.format(Locale.getDefault(), "%s%.2f", currency, item.getItemCost()));

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

    public void addAll(ArrayList<ItemInvoice> items) {
        mItems = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public void addAll(ItemInvoice[] items) {
        mItems = new ArrayList<>(Arrays.asList(items));
        notifyDataSetChanged();
    }

    public void clear() {
        mItems = null;
    }

    public static class ItemInvoiceVH extends RecyclerView.ViewHolder {

        private final TextView itemName, itemCategory, itemCost, itemDateTime;
        private final Context context;


        public ItemInvoiceVH(View v) {
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
