package com.example.expensecalculatorapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.ExpenseRecyclerHolder> {

    List<String> expenseNames;
    List<Double> expenseAmounts;
    List<LocalDate> expenseDates;
    Context context;
    public ExpenseRecyclerAdapter(Context context, List<String> expenseNames, List<Double> expenseAmounts, List<LocalDate> expenseDates)
    {
        this.context = context;
        this.expenseNames = expenseNames;
        this.expenseAmounts = expenseAmounts;
        this.expenseDates = expenseDates;
    }

    @NonNull
    @Override
    public ExpenseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense_recycler_row, parent, false);
        return new ExpenseRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseRecyclerHolder holder, int position) {
        holder.nameTextView.setText(expenseNames.get(position));
        holder.amountTextView.setText(expenseAmounts.get(position).toString());
        String dateString = Converters.localDateToString(expenseDates.get(position));
        holder.dateTextView.setText(dateString);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You clicked " + expenseNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("view expenses page", "recycler size: " + expenseNames.size());
        return expenseNames.size();
    }

    public class ExpenseRecyclerHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, amountTextView, dateTextView;
        ConstraintLayout layout;
        public ExpenseRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.expense_row_title);
            amountTextView = itemView.findViewById(R.id.expense_row_amount);
            dateTextView = itemView.findViewById(R.id.expense_row_date);
            layout = itemView.findViewById(R.id.recycler_row);
        }
    }
}
