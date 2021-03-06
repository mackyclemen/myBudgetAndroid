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
import static com.mackyc.projects.mybudget.DashboardActivity.itemArrayList;

public class HomeFragment extends Fragment {

    LinearLayout costBreakdownList;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);

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

            for (int pos = 0; pos < Math.min(itemArrayList.size(), 3); pos++) {

                View listView = listInflater.inflate(R.layout.listitem_breakdown, costBreakdownList, false);
                CostBreakdownItem currentItem = itemArrayList.get(pos);

                final TextView categoryName = listView.findViewById(R.id.categoryName);
                final TextView categoryTender = listView.findViewById(R.id.categoryTender);
                final ProgressBar categoryProgress = listView.findViewById(R.id.categoryProgress);

                String itemCategoryStr;

                switch (currentItem.getCategory()) {
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

                categoryName.setText(itemCategoryStr);
                categoryTender.setText(String.format(Locale.getDefault(), "%s%.2f", "PHP", currentItem.getCategoryCost()));
                categoryProgress.setMax((int) currentCost);
                categoryProgress.setProgress((int) currentItem.getCategoryCost());

                costBreakdownList.addView(listView);

            }

        }

        return v;
    }
}
