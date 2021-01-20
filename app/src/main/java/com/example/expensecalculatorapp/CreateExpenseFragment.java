package com.example.expensecalculatorapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateExpenseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AutoCompleteTextView expenseTitleTextView, memoTextView;
    Spinner categorySpinner, currencySpinner, paymentTypeSpinner, accountSpinner;
    FloatingActionButton addCategoryButton, addAccountButton;
    EditText amountEditText;
    Button selectDateButton, submitButton;
    TextView dateTextView;

    int textColor;

    DatePickerDialog datePickerDialog;
    Calendar dateSelected;

    List<String> categoryList;
    List<String> accountList;

    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> accountAdapter;

    FileManagement fileManagement;

    final String categoryFile = "expenseCategoryList.txt";
    final String accountFile = "expenseAccountList.txt";
    public CreateExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateExpenseFragment newInstance(String param1, String param2) {
        CreateExpenseFragment fragment = new CreateExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileManagement = new FileManagement();
        categoryList = fileManagement.ReadListFromFile(categoryFile, getActivity());
        accountList = fileManagement.ReadListFromFile(accountFile, getActivity());
        final Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(getContext(),this,currentYear,currentMonth,currentDay);

        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                textColor = Color.WHITE;
                break;

            case Configuration.UI_MODE_NIGHT_NO:

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                textColor = Color.BLACK;
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_expense, container, false);

        categorySpinner = v.findViewById(R.id.categorySpinner);
        currencySpinner = v.findViewById(R.id.currencySpinner);
        paymentTypeSpinner = v.findViewById(R.id.paymentTypeSpinner);
        accountSpinner = v.findViewById(R.id.accountSpinner);

        expenseTitleTextView = v.findViewById(R.id.expenseTitleTextView);
        amountEditText = v.findViewById(R.id.amountNumberDecimal);
        memoTextView = v.findViewById(R.id.memoAutoCompleteTextView);

        selectDateButton = v.findViewById(R.id.selectDateButton);
        submitButton = v.findViewById(R.id.submitButton);
        addAccountButton = v.findViewById(R.id.addAccountFloatingActionButton);
        addCategoryButton = v.findViewById(R.id.addCategoryFloatingActionButton);

        dateTextView = v.findViewById(R.id.dateTextView);
        dateTextView.setText("");

        paymentTypeSpinner.setOnItemSelectedListener(this);
        categorySpinner.setOnItemSelectedListener(this);
        currencySpinner.setOnItemSelectedListener(this);
        accountSpinner.setOnItemSelectedListener(this);

        selectDateButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        addAccountButton.setOnClickListener(this);
        addCategoryButton.setOnClickListener(this);

        categoryAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        accountAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.expense_currency_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> paymentTypeAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.payment_type_array, android.R.layout.simple_spinner_dropdown_item);


        categorySpinner.setAdapter(categoryAdapter);
        currencySpinner.setAdapter(currencyAdapter);
        paymentTypeSpinner.setAdapter(paymentTypeAdapter);
        accountSpinner.setAdapter(accountAdapter);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == selectDateButton) {
            datePickerDialog.show();

        } else if (v == submitButton) {

        } else if(v == addCategoryButton) {
            addCategory();
        } else if(v == addAccountButton) {
            addAccount();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int pos, long id) {
        //FIXME: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setTextColor(int)' on a null object reference
        if (adapterView.getCount() != 0) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(textColor);
            ((TextView) adapterView.getChildAt(0)).setTextSize(20);
        }
        if (adapterView.getId() == R.id.paymentTypeSpinner) {
            Log.d("payment spinner", "Reached first condition");
            Log.d("payment spinner", "onItemSelected: adapterView.getItemAtPosition(pos) = " + adapterView.getItemAtPosition(pos));
            if (adapterView.getItemAtPosition(pos).toString().equals("Debit") || adapterView.getItemAtPosition(pos).toString().equals("Credit")) {
                Log.d("payment spinner", "Reached second condition");
                getView().findViewById(R.id.accountSpinner).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.addAccountFloatingActionButton).setVisibility(View.VISIBLE);
            } else {
                getView().findViewById(R.id.accountSpinner).setVisibility(View.GONE);
                getView().findViewById(R.id.addAccountFloatingActionButton).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        int realMonth = month + 1;
        dateTextView.setText(day + "/" + realMonth + "/" + year);
        dateSelected = Calendar.getInstance();
        dateSelected.set(year, month, day);
    }

    private void addCategory()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add new category");

        final View dialogLayout = getLayoutInflater().inflate(R.layout.add_diaglog_layout, null);
        EditText dialogText = dialogLayout.findViewById(R.id.addEditText);
        dialogText.setHint("Category");
        builder.setView(dialogLayout);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText dialogText = dialogLayout.findViewById(R.id.addEditText);
                fileManagement.WriteStringToFile(dialogText.getText().toString(), categoryFile, getActivity());
                categoryList.add(dialogText.getText().toString());
                categoryAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Close dialog and do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addAccount()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add new account");

        final View dialogLayout = getLayoutInflater().inflate(R.layout.add_diaglog_layout, null);
        EditText dialogText = dialogLayout.findViewById(R.id.addEditText);
        dialogText.setHint("Account");
        builder.setView(dialogLayout);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText dialogText = dialogLayout.findViewById(R.id.addEditText);
                fileManagement.WriteStringToFile(dialogText.getText().toString().trim(), accountFile, getActivity());
                accountList.add(dialogText.getText().toString().trim());
                accountAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Close dialog and do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}