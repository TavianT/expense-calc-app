package com.example.expensecalculatorapp;

import com.example.expensecalculatorapp.FileManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView  bottomNavigationView;
    NavController navController;

    FileManagement fileManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_menu);
        navController = Navigation.findNavController(this,R.id.fragment);

        //TODO: Add visualiseExpensesFragment and aboutFragment to this
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homePageFragment,R.id.createExpenseFragment).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        fileManagement = new FileManagement();

        String categoryFileName = "expenseCategoryList.txt";
        String accountFileName = "expenseAccountList.txt";

        if(!fileManagement.fileExists(categoryFileName)) {
            String[] categoryArray = {"Food", "Bills", "Travel", "Home", "Entertainment", "Leisure", "Shopping", "Clothing", "Insurance", "Tax", "Healthcare", "Gift", "Other"};
            List<String> categoryList = new ArrayList<>(Arrays.asList(categoryArray));
            createListFile(fileManagement, categoryFileName, categoryList);
        }
        if(!fileManagement.fileExists(accountFileName)) {
            String[] accountArray = {"PayPal", "HSBC", "Lloyds", "Halifax", "Barclays", "RBS", "TSB", "Natwest", "Monzo", "Nationwide", "Santander", "Co-operative", "Tesco", "Other"};
            List<String> accountList = new ArrayList<>(Arrays.asList(accountArray));
            createListFile(fileManagement, accountFileName, accountList);
        }

        
    }

    private void createListFile(FileManagement fileManagement, String fileName,List<String> list)
    {
            fileManagement.WriteListToFile(list, fileName, this);
    }

    public void startSingleExpenseFragment(Expense expense, String dateString)
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", expense.expenseName);
        bundle.putString("category", expense.category);
        bundle.putDouble("amount", expense.amount);
        bundle.putString("date", dateString);
        bundle.putString("type", expense.type);
        bundle.putString("account", expense.account);
        bundle.putString("memo", expense.memo);
        FragmentManager manager = getSupportFragmentManager();
        SingleExpenseFragment singleExpenseFragment = new SingleExpenseFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_activity, singleExpenseFragment.getClass(), bundle, null).addToBackStack(null);
        transaction.commit();
       /* try {
            getFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.main_activity, SingleExpenseFragment.class.newInstance(), bundle)
                    .commit();
        } catch (IllegalAccessException e) {
            Log.e("startSingleExpenseFragment",  e.toString());
        } catch (InstantiationException e) {
            Log.e("startSingleExpenseFragment",  e.toString());
        } */
    }
}