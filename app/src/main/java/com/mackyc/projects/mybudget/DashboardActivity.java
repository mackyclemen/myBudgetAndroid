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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int ADD_NEW_TRANSACTION_REQUEST = 0;
    static final int MODIFY_TRANSACTION_REQUEST = 1;

    static CostBreakdownItem[] costBreakdownItems = {
            /* new CostBreakdownItem("Food", 1080),
            new CostBreakdownItem("Essentials", 360) */
    };

    static HistoryBreakdownItem[] baseItemList = {
            new HistoryBreakdownItem(1, "Nagaraya", CostBreakdownItem.CATEGORY_FOOD, 25.00),
            new HistoryBreakdownItem(2, "Smart C", CostBreakdownItem.CATEGORY_FOOD, 25.00),
            new HistoryBreakdownItem(3, "Quiz Booklet", CostBreakdownItem.CATEGORY_OTHERS, 10.00)
    };

    static ArrayList<HistoryBreakdownItem> breakdownItems = new ArrayList<>(Arrays.asList(baseItemList));

    static double currentCost = 1440.00;
    static String currency = "PHP";

    FragmentManager manager;
    FragmentTransaction transaction;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

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
            if (currentFragment instanceof HistoryFragment)
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
                break;

            case R.id.nav_history:
                if (!(fragment instanceof HistoryFragment)) {
                    transaction.replace(R.id.fragmentContainer, new HistoryFragment())
                            .addToBackStack("HistoryFragment");
                }
                break;

            default:
                Toast.makeText(this, "Unimplemented", Toast.LENGTH_SHORT)
                        .show();
        }

        transaction.commit();

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cardOnClickTransaction(View view) {
        switch (view.getId()) {
            case R.id.breakdownCard:
                navigationView.setCheckedItem(R.id.nav_history);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, new HistoryFragment())
                        .addToBackStack("HistoryFragment")
                        .commit();
        }

    }
}
