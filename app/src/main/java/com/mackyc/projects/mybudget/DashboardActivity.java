package com.mackyc.projects.mybudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int ADD_NEW_TRANSACTION_REQUEST = 0;
    static final int MODIFY_TRANSACTION_REQUEST = 1;

    static CostBreakdownItem[] costBreakdownItems = {
            new CostBreakdownItem(CostBreakdownItem.CATEGORY_FOOD, 50.00),
            new CostBreakdownItem(CostBreakdownItem.CATEGORY_OTHERS, 2510.00)
    };

    static ItemInvoice[] baseItemList = {
            new ItemInvoice(1, "Nagaraya", CostBreakdownItem.CATEGORY_FOOD, 25.00, "Not so healthy, but my hunger should suffice a bit."),
            new ItemInvoice(2, "Smart C", CostBreakdownItem.CATEGORY_FOOD, 25.00, "A healthy alternative to carbonated drinks."),
            new ItemInvoice(3, "Quiz Booklet", CostBreakdownItem.CATEGORY_OTHERS, 10.00),
            new ItemInvoice(4, "DO_NOT_USE", CostBreakdownItem.CATEGORY_OTHERS, 2500.00, "What is this even?")
    };

    static ArrayList<ItemInvoice> breakdownItems = new ArrayList<>(Arrays.asList(baseItemList));
    static ArrayList<CostBreakdownItem> itemArrayList = new ArrayList<>(Arrays.asList(costBreakdownItems));

    static double currentCost;
    static double currentSavings = 150.00;
    static String currency = "PHP";

    FragmentManager manager;
    FragmentTransaction transaction;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (ItemInvoice item:breakdownItems) {
            currentCost += item.getItemCost();
        }

        setContentView(R.layout.activity_dashboard);

        TextView currency_cost = findViewById(R.id.currency_currentCost);
        TextView currency_savings = findViewById(R.id.currency_currentSavings);
        TextView cost = findViewById(R.id.currentCost);
        TextView savings = findViewById(R.id.currentSavings);
        ProgressBar costBar = findViewById(R.id.costBar);


        if (currentCost < currentSavings) {
            costBar.setMax((int) currentSavings);
            costBar.setProgress((int) currentCost);
        } else {
            costBar.setMax(100);
            costBar.setProgress(100);
            savings.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }

        currency_cost.setText(currency);
        currency_savings.setText(currency);
        cost.setText(String.format(Locale.getDefault(), "%.2f", currentCost));
        savings.setText(String.format(Locale.getDefault(), "%.2f", currentSavings));

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();


        transaction.add(R.id.fragmentContainer, new HomeFragment(), "HomeFragment")
                .commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent i = new Intent(getApplicationContext(), AddTransactionActivity.class);
                i.putExtra("requestCode", ADD_NEW_TRANSACTION_REQUEST);
                startActivityForResult(i, ADD_NEW_TRANSACTION_REQUEST);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_NEW_TRANSACTION_REQUEST:
                if (resultCode == Activity.RESULT_OK) {

                    // Do something
                }

                if (resultCode == Activity.RESULT_CANCELED) {
                    Snackbar.make(findViewById(R.id.dash), "Data discarded", Snackbar.LENGTH_LONG)
                            .show();
                }
                break;


        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Fragment currentFragment = manager.findFragmentById(R.id.fragmentContainer);
            if (currentFragment instanceof HomeFragment)
                navigationView.setCheckedItem(R.id.nav_home);
            if (currentFragment instanceof ItemListFragment)
                navigationView.setCheckedItem(R.id.nav_history);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // TODO: 12/10/2018 update the fragment container to categories
        // TODO: 12/11/2018 add the remaining fragments

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        transaction = manager.beginTransaction();

        switch (item.getItemId()) {

            case R.id.nav_home:
                if (!(fragment instanceof HomeFragment))
                    manager.popBackStack();
                transaction.replace(R.id.fragmentContainer, new HomeFragment());
                transaction.commit();
                break;

            case R.id.nav_history:
                if (!(fragment instanceof ItemListFragment)) {
                    transaction.replace(R.id.fragmentContainer, new ItemListFragment())
                            .addToBackStack("ItemListFragment");
                    transaction.commit();
                }
                break;

            default:
                Toast.makeText(this, "Unimplemented", Toast.LENGTH_SHORT)
                        .show();
        }


        // Handle navigation view item clicks here.
        //int id = item.getItemId();

        /*

        switch (item.getItemId()) {

        } */

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cardOnClickTransaction(View view) {
        switch (view.getId()) {
            case R.id.breakdownCard:
                navigationView.setCheckedItem(R.id.nav_history);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, new ItemListFragment())
                        .addToBackStack("ItemListFragment")
                        .commit();
        }

    }
}
