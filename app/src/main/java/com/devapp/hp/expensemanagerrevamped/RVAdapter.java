package com.devapp.hp.expensemanagerrevamped;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.devapp.hp.expensemanagerrevamped.MainActivity.Expense;
import static com.devapp.hp.expensemanagerrevamped.MainActivity.ExpenseName;
import static com.devapp.hp.expensemanagerrevamped.MainActivity.myDatabase;

/**
 * Created by HP on 07-06-2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>  {



    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        ImageView background;

        TextView amount;


        PersonViewHolder(final View itemView){
            super(itemView);


            name = (TextView)itemView.findViewById(R.id.nameTextView);

            background = (ImageView)itemView.findViewById(R.id.imageView);

            amount = (TextView) itemView.findViewById(R.id.amountTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();
                    //Toast.makeText(itemView.getContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();

                    new AlertDialog.Builder(itemView.getContext())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Delete?")
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    myDatabase.execSQL("DELETE FROM expenses WHERE name='"+ExpenseName.get(position)+"' AND amount='"+Expense.get(position)+"'");

                                    ExpenseName.remove(position);
                                    Expense.remove(position);
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton("Cancel",null)
                            .show();

                }
            });

        }

    }


    @Override
    public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        PersonViewHolder pvh = new PersonViewHolder(v);

        return pvh;


    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

        holder.name.setText(ExpenseName.get(position));

        holder.amount.setText(String.valueOf(Expense.get(position)));



    }

    @Override
    public int getItemCount() {
        return Expense.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);


    }
}

