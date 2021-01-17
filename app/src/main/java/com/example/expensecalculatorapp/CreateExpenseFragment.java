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

    AutoCompleteTextView expenseTitleTextView;
    Spinner typeSpinner;
    Spinner currencySpinner;
    EditText amountEditText;
    Button selectDateButton;
    TextView dateTextView;
    AutoCompleteTextView memoTextView;
    Button submitButton;

    int textColor;

    List<String> typeList;

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
        typeList = fileManagement.ReadListFromFile("expenseTypeList.txt",getActivity());

        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                textColor = Color.WHITE;
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                textColor = Color.BLACK;
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                textColor = Color.BLACK;
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_expense, container, false);
        expenseTitleTextView = v.findViewById(R.id.expenseTitleTextView);
        typeSpinner = v.findViewById(R.id.typeSpinner);
        currencySpinner = v.findViewById(R.id.currencySpinner);
        amountEditText = v.findViewById(R.id.amountNumberDecimal);
        selectDateButton = v.findViewById(R.id.selectDateButton);
        dateTextView = v.findViewById(R.id.dateTextView);
        submitButton = v.findViewById(R.id.submitButton);
        memoTextView = v.findViewById(R.id.memoAutoCompleteTextView);

        dateTextView.setText("");

        typeSpinner.setOnItemSelectedListener(this);
        currencySpinner.setOnItemSelectedListener(this);
        selectDateButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, typeList);

        typeSpinner.setAdapter(typeAdapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == selectDateButton) {

        } else if (v == submitButton) {

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int pos, long id) {
        //FIXME: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setTextColor(int)' on a null object reference
        if (adapterView.getCount() != 0) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(textColor);
            ((TextView) adapterView.getChildAt(0)).setTextSize(20);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}