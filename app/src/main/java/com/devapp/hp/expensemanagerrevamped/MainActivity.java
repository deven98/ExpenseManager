package com.devapp.hp.expensemanagerrevamped;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {

    FrameLayout fl;

    static SQLiteDatabase myDatabase;

    public static ArrayList<Float> Expense = new ArrayList<>();
    public static ArrayList<String> ExpenseName = new ArrayList<>();

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.alert_dark_frame)
                .setTitle("Are you sure you want to exit?")
                .setMessage("Press Yes to quit or Cancel to go back")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Expense.clear();
                        ExpenseName.clear();
                        MainActivity.super.onBackPressed();

                    }
                }).setNegativeButton("Cancel",null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            myDatabase = this.openOrCreateDatabase("Expenses", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS expenses (name VARCHAR,amount DOUBLE)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM expenses", null);

            int nameIndex = c.getColumnIndex("name");
            int amountIndex = c.getColumnIndex("amount");

            c.moveToFirst();

            while (c != null) {
                Log.i("Name", Integer.toString(c.getInt(nameIndex)));
                ExpenseName.add(c.getString(nameIndex));
                Log.i("Age", c.getString(amountIndex));
                Expense.add(Float.parseFloat(c.getString(amountIndex)));
                c.moveToNext();
            }

        }catch (Exception e){

        }


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

         fl = (FrameLayout) findViewById(R.id.fragment_container);


        for (int i=1; i <= 2; i++) {
            android.support.v7.app.ActionBar.Tab tab = actionBar.newTab();

            if(i==1){
            tab.setText("Dashboard");
            }
            else if(i==2){
                tab.setText("Expenses");
            }
            tab.setTabListener(this);
            actionBar.addTab(tab);

        }


        if (findViewById(R.id.fragment_container) != null) {


            if (savedInstanceState != null) {
                return;
            }


            DashboardFragment firstFragment = new DashboardFragment();


            firstFragment.setArguments(getIntent().getExtras());


            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }

    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        if (findViewById(R.id.fragment_container) != null) {

            if(tab.getPosition() == 0){
            DashboardFragment firstFragment = new DashboardFragment();

            fl.removeAllViews();

            firstFragment.setArguments(getIntent().getExtras());


            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();}
            else if(tab.getPosition()==1){
                ExpenseFragment firstFragment = new ExpenseFragment();

                firstFragment.setArguments(getIntent().getExtras());

                fl.removeAllViews();

                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }
        }
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}