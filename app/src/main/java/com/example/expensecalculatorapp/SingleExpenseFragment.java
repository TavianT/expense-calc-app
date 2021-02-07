package com.example.expensecalculatorapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleExpenseFragment extends Fragment {

    TextView titleTextView, categoryTextView, amountTextView, dateTextView, typeTextView, accountTextView, memoTextView;

    String expenseName, category, date, type, account, memo;
    double amount;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingleExpenseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SingleExpenseFragment newInstance(Bundle args) {
        SingleExpenseFragment fragment = new SingleExpenseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseName = requireArguments().getString("name");
        category = requireArguments().getString("category");
        amount = requireArguments().getDouble("amount");
        date = requireArguments().getString("date");
        type = requireArguments().getString("type");
        account = requireArguments().getString("account");
        memo = requireArguments().getString("memo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_single_expense, container, false);
        titleTextView = v.findViewById(R.id.singleExpenseTitleTextView);
        categoryTextView = v.findViewById(R.id.singleExpenseCategoryTextView);
        amountTextView = v.findViewById(R.id.singleExpenseAmountTextView);
        dateTextView = v.findViewById(R.id.singleExpenseDateTextView);
        typeTextView = v.findViewById(R.id.singleExpenseTypeTextView);
        accountTextView = v.findViewById(R.id.singleExpenseAccountTextView);
        memoTextView = v.findViewById(R.id.singleExpenseMemoTextView);

        titleTextView.setText(expenseName);
        categoryTextView.setText(category);
        amountTextView.setText(String.valueOf(amount));
        dateTextView.setText(date);
        typeTextView.setText(type);
        if (!account.equals("")) {
            accountTextView.setText(account);
        } else {
            accountTextView.setVisibility(View.GONE);
        }
        memoTextView.setText(memo);

        return v;
    }
}