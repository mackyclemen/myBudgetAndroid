package com.mackyc.projects.mybudget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
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
