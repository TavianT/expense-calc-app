package com.example.expensecalculatorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    }

    @Override
    public int getItemCount() {
        return expenseNames.size();
    }

    public class ExpenseRecyclerHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, amountTextView, dateTextView;
        public ExpenseRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.expense_row_title);
            amountTextView = itemView.findViewById(R.id.expense_row_amount);
            dateTextView = itemView.findViewById(R.id.expense_row_date);
        }
    }
}
