package com.example.expensecalculatorapp;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewExpensesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewExpensesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Expense> expenses;
    List<String> expenseNames;
    List<LocalDate> expenseDates;
    List<Double> expenseAmounts;

    RecyclerView recyclerView;
    public ViewExpensesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewExpensesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewExpensesFragment newInstance(String param1, String param2) {
        ViewExpensesFragment fragment = new ViewExpensesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseNames = new ArrayList<String>();
        expenseDates = new ArrayList<LocalDate>();
        expenseAmounts = new ArrayList<Double>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            AppDatabase db = AppDatabase.getInstance(requireContext());
            ExpenseDao dao = db.expenseDAO();
            expenses = dao.getAll();
        });
        try {
            Log.i("Executor obj", "attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Log.e("Executor obj", "Task interrupted: " + e.toString());
        } finally {
            if (!executor.isTerminated()) {
                Log.e("Executor obj", "Cancel non finished tasks");
            }
            executor.shutdownNow();
            Log.d("Executor obj", "shutdown finished");

        }

        int i = 0; //DELETE LATER
        for (final Expense expense : expenses) {
            expenseNames.add(expense.expenseName);
            Log.d("Expense values from db", "Expense name: " + expenseNames.get(i));
            expenseAmounts.add(expense.amount);
            expenseDates.add(expense.date);
            i++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_expenses, container, false);
        recyclerView = v.findViewById(R.id.expensesRecyclerView);

        ExpenseRecyclerAdapter expenseRecyclerAdapter = new ExpenseRecyclerAdapter(requireContext(), expenseNames, expenseAmounts, expenseDates);
        recyclerView.setAdapter(expenseRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //FIXME: Figure out a way to get rid of the back arrow
        /*ActionBar actionBar = requireActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }*/
        return v;
    }
}