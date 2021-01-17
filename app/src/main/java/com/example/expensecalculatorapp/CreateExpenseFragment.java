package com.example.expensecalculatorapp;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateExpenseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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

    List<String> categoryList;

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

        FileManagement fileManagement = new FileManagement();
        categoryList = fileManagement.ReadListFromFile("expenseCategoryList.txt",getActivity());

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

        dateTextView = v.findViewById(R.id.dateTextView);
        dateTextView.setText("");

        paymentTypeSpinner.setOnItemSelectedListener(this);
        categorySpinner.setOnItemSelectedListener(this);
        currencySpinner.setOnItemSelectedListener(this);
        accountSpinner.setOnItemSelectedListener(this);

        selectDateButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.expense_currency_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> paymentTypeAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.payment_type_array, android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(categoryAdapter);
        currencySpinner.setAdapter(currencyAdapter);
        paymentTypeSpinner.setAdapter(paymentTypeAdapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == selectDateButton) {

        } else if (v == submitButton) {
          /*  getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getView().findViewById(R.id.accountSpinner).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.addAccountFloatingActionButton).setVisibility(View.VISIBLE);
                }
            }); */
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
}