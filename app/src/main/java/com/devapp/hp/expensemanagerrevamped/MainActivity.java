package com.devapp.hp.expensemanagerrevamped;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {

    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

         fl = (FrameLayout) findViewById(R.id.fragment_container);


        for (int i=1; i <= 2; i++) {
            android.support.v7.app.ActionBar.Tab tab = actionBar.newTab();
            tab.setText("Tab " + i);
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