package com.mackyc.projects.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ItemInvoiceNotesAdapter extends RecyclerView.Adapter<ItemInvoiceNotesAdapter.ItemInvoiceNotesVH> {

    private static ArrayList<ItemInvoice> mItemInvoices = new ArrayList<>();
    private Context context;

    public ItemInvoiceNotesAdapter(Context context, ItemInvoice[] itemInvoices) {
        this.context = context;
        for (ItemInvoice item:itemInvoices) {
            if(item.getNote() != null) {
                mItemInvoices.add(item);
            }
        }
    }

    public ItemInvoiceNotesAdapter(Context context, ArrayList<ItemInvoice> itemInvoices) {
        this.context = context;
        for (ItemInvoice item:itemInvoices) {
            if(item.getNote() != null) {
                mItemInvoices.add(item);
            }
        }
    }

    @NonNull
    @Override
    public ItemInvoiceNotesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_notes, parent, false);

        return new ItemInvoiceNotesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemInvoiceNotesVH holder, int position) {
        ItemInvoice item = mItemInvoices.get(position);
        holder.getNoteContent().setText(item.getNote());

        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        String formattedDate = sdf.format(item.getItemDateTime());
        holder.getNoteID().setText(String.format(Locale.getDefault(), "%s, %s", item.getItemName(), formattedDate));
    }

    @Override
    public int getItemCount() {
        return mItemInvoices.size();
    }

    public static class ItemInvoiceNotesVH extends RecyclerView.ViewHolder{

        private final TextView noteContent, noteID;
        private final Context context;

        public ItemInvoiceNotesVH(View itemView) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.notes_cont);
            noteID = itemView.findViewById(R.id.notes_purchaseIdent);
            context = itemView.getContext();
        }

        public TextView getNoteContent() {
            return noteContent;
        }

        public TextView getNoteID() {
            return noteID;
        }

        public Context getContext() {
            return this.context;
        }
    }


}
