package com.devapp.hp.expensemanagerrevamped;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import static com.devapp.hp.expensemanagerrevamped.MainActivity.Expense;
import static com.devapp.hp.expensemanagerrevamped.MainActivity.ExpenseName;
import static com.devapp.hp.expensemanagerrevamped.MainActivity.myDatabase;
import static com.devapp.hp.expensemanagerrevamped.R.styleable.AlertDialog;

/**
 * Created by HP on 07-06-2017.
 */

public class ExpenseFragment extends Fragment {

    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.expense_layout, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        rv.setLayoutManager(layoutManager);

        final  RVAdapter adapter = new RVAdapter();

        rv.setAdapter(adapter);

        //Toast.makeText(getActivity() , "Hi there!", Toast.LENGTH_SHORT).show();

        FloatingActionButton fb = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_input_add)
                        .setTitle("Add an expense")
                        .setView(R.layout.add_dialog)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                EditText name = (EditText) ((Dialog)dialog).findViewById(R.id.dialogName);
                                EditText amount = (EditText) ((Dialog)dialog).findViewById(R.id.dialogAmount);

                                if(name.getText().toString().matches("") || amount.getText().toString().matches("")){
                                    Toast.makeText(v.getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                ExpenseName.add(name.getText().toString());
                                Expense.add(Float.parseFloat(amount.getText().toString()));

                                myDatabase.execSQL("INSERT INTO expenses(name,amount) VALUES ('" + name.getText().toString() + "', " + amount.getText().toString() + ") ");

                                adapter.notifyDataSetChanged();}
                            }
                        }).show();

            }
        });

        return v;
    }
}
