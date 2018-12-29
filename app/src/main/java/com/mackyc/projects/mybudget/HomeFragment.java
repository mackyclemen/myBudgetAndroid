package com.mackyc.projects.mybudget;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import static com.mackyc.projects.mybudget.DashboardActivity.costBreakdownItems;
import static com.mackyc.projects.mybudget.DashboardActivity.currentCost;

public class HomeFragment extends Fragment {

    LinearLayout costBreakdownList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        costBreakdownList = v.findViewById(R.id.breakdownCard_list);

        if (costBreakdownItems.length == 0) {

            TextView noItemsTextView = new TextView(getContext());
            noItemsTextView.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );
            noItemsTextView.setPadding(0, 16, 0, 16);
            noItemsTextView.setText("Add purchases to see your breakdown for today");
            noItemsTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            noItemsTextView.setTypeface(null, Typeface.ITALIC);

            costBreakdownList.addView(noItemsTextView);

        } else {

            LayoutInflater listInflater = getLayoutInflater();
            for (int pos = 0; pos < Math.min(costBreakdownItems.length, 3); pos++) {

                View listView = listInflater.inflate(R.layout.listitem_breakdown, costBreakdownList, false);
                CostBreakdownItem currentItem = costBreakdownItems[pos];

                final TextView categoryName = listView.findViewById(R.id.categoryName);
                final TextView categoryTender = listView.findViewById(R.id.categoryTender);
                final ProgressBar categoryProgress = listView.findViewById(R.id.categoryProgress);

                categoryName.setText(currentItem.getCategoryName());
                categoryTender.setText(String.format(Locale.getDefault(), "%s%.0f", "PHP", currentItem.getCategoryCost()));
                categoryProgress.setMax((int) currentCost);
                categoryProgress.setProgress((int) currentItem.getCategoryCost());

                costBreakdownList.addView(listView);

            }

        }

        return v;
    }
}
