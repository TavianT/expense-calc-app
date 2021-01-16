package com.example.expensecalculatorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView linkedInImageView;
    private ImageView githubImageView;

    private Button createExpenseButton;
    private Button viewExpensesButton;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        linkedInImageView = getView().findViewById(R.id.linkedInLogo);
        githubImageView = getView().findViewById(R.id.githubLogo);
        createExpenseButton = getView().findViewById(R.id.createExpenseButton);
        viewExpensesButton = getView().findViewById(R.id.viewExpensesButton);

        linkedInImageView.setOnClickListener(this);
        githubImageView.setOnClickListener(this);
        createExpenseButton.setOnClickListener(this);
        viewExpensesButton.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onClick(View v) {
        Intent browserIntent;
        if (v == linkedInImageView) {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/tavian-taylor-86ba8b18a/"));
            startActivity(browserIntent);
        } else if (v == githubImageView) {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TavianT/expense-calc-app"));
            startActivity(browserIntent);
        } /*else if (v == createExpenseButton) {
            Intent createExpenseIntent = new Intent(this, CreateExpenseActivity.class);
        } else if (v == viewExpensesButton) {
            Intent createExpenseIntent = new Intent(this, ViewExpensesActivity.class);
        }*/
    }
}