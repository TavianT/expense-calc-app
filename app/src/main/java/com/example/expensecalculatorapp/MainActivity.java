package com.example.expensecalculatorapp;

import com.example.expensecalculatorapp.FileManagement;

import androidx.appcompat.app.AppCompatActivity;
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

        //TODO: Add viewExpensesFragment and aboutFragment to this
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
}