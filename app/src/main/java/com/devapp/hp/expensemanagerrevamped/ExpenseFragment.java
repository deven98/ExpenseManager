package com.devapp.hp.expensemanagerrevamped;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by HP on 07-06-2017.
 */

public class ExpenseFragment extends Fragment {

    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.expense_layout, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        rv.setLayoutManager(layoutManager);

        RVAdapter adapter = new RVAdapter();

        rv.setAdapter(adapter);

        Toast.makeText(getActivity() , "Hi there!", Toast.LENGTH_SHORT).show();

        return v;
    }
}
