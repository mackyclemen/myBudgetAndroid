package com.mackyc.projects.mybudget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class AddTransactionActivity extends AppCompatActivity {

    static final int ADD_NEW_TRANSACTION_REQUEST = 0;
    static final int MODIFY_TRANSACTION_REQUEST = 1;

    int itemID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_transaction);

        Spinner txnCategory = findViewById(R.id.txnCategory);
        RadioGroup txnType = findViewById(R.id.TransactionType);
        EditText txnDesc = findViewById(R.id.txnDesc);
        EditText txnAmt = findViewById(R.id.txnAmount);


        Bundle b = getIntent().getExtras();

        if (b != null) {
            switch (b.getInt("requestCode")) {
                case ADD_NEW_TRANSACTION_REQUEST:
                    setTitle(R.string.AddTransaction);
                    break;

                case MODIFY_TRANSACTION_REQUEST:
                    setTitle(R.string.ModifyTransaction);

                    txnDesc.setText(b.getString("TxnName"));
                    txnAmt.setText(String.format(Locale.getDefault(), "%.00f", b.getDouble("TxnCost")));

                    if (b.getBoolean("TxnCredit")) {
                        txnType.check(R.id.RemoveBudgetRadio);
                    } else {
                        txnType.check(R.id.AddBudgetRadio);
                    }

                    break;
            }
        }

        // TODO: 1/2/2019 Implement editing tool

/*      public void onClick(View v) {
            Intent i = new Intent(getContext(), AddTransactionActivity.class);
            i.putExtra("TxnName", mItems.get(getAdapterPosition()).getItemName());
            i.putExtra("TxnCredit", mItems.get(getAdapterPosition()).isCredit());
            i.putExtra("TxnCost", mItems.get(getAdapterPosition()).getItemCost());
            i.putExtra("TxnCategory", mItems.get(getAdapterPosition()).getItemCategory());
            ((Activity) context).startActivityForResult(i, DashboardActivity.MODIFY_TRANSACTION_REQUEST);
        }*/

        // enum Category {IMPORTANT, UTILITIES, FOOD, TRANSPORTATION, PERSONAL, ENTERTAINMENT, SUPPLIES, OTHERS}



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_transaction, menu);
        return true;
    }


    //@Override
    //public void onBackPressed() {
    // check if all required data is complete

    // else, ask and discard
        /*
        AlertDialog.Builder d = new AlertDialog.Builder(this);

        d.setMessage(R.string.dialogDiscard);
        d.setPositiveButton(R.string.dialogPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        d.setNegativeButton(R.string.dialogNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing

            }
        });
        d.create();
        d.show();
        */
        /*
        setResult(Activity.RESULT_CANCELED);
        finish();
        */
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // check if data is complete

                setResult(Activity.RESULT_CANCELED); // TODO: 12/28/2018 REPLACE WITH WORKING METHOD
                finish();
                // if not, alert user


                return true;

            case R.id.action_settings:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
