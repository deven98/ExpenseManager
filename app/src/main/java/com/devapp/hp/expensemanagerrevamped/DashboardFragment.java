package com.devapp.hp.expensemanagerrevamped;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.devapp.hp.expensemanagerrevamped.MainActivity.Expense;

/**
 * Created by HP on 07-06-2017.
 */

public class DashboardFragment extends Fragment {

    TextView totalAmount;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dashboard, container, false);

        totalAmount = (TextView) v.findViewById(R.id.TotalAmount);

        float sum = 0;

        for(int i =0; i < Expense.size(); i++){

            sum = sum + Expense.get(i);

        }

        totalAmount.setText(String.valueOf(sum));

        return v;
    }
}
