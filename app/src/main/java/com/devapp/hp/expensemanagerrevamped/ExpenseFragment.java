package com.devapp.hp.expensemanagerrevamped;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by HP on 07-06-2017.
 */

public class ExpenseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity() , "Hi there!", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.expense_layout, container, false);
    }
}
