package com.example.expensecalculatorapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.ExpenseRecyclerHolder> {

    List<String> expenseNames;
    List<Double> expenseAmounts;
    List<LocalDate> expenseDates;
    List<Expense> expenses;
    Context context;
    public ExpenseRecyclerAdapter(Context context, List<String> expenseNames, List<Double> expenseAmounts, List<LocalDate> expenseDates, List<Expense> expenses)
    {
        this.context = context;
        this.expenseNames = expenseNames;
        this.expenseAmounts = expenseAmounts;
        this.expenseDates = expenseDates;
        this.expenses = expenses;
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
                Bundle bundle = new Bundle();
                bundle.putString("name", expenses.get(position).expenseName);
                bundle.putString("category", expenses.get(position).category);
                bundle.putDouble("amount", expenses.get(position).amount);
                bundle.putString("date", dateString);
                bundle.putString("type", expenses.get(position).type);
                bundle.putString("account", expenses.get(position).account);
                bundle.putString("memo", expenses.get(position).memo);
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                SingleExpenseFragment singleExpenseFragment = SingleExpenseFragment.newInstance(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity, singleExpenseFragment).addToBackStack(null).commit();
                /*((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.main_activity, SingleExpenseFragment.class, bundle)
                        .commit();*/
                //Toast.makeText(context,"You clicked " + expenseNames.get(position), Toast.LENGTH_SHORT).show();
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
