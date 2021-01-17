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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView  bottomNavigationView;
    NavController navController;
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

        setupTypeList();
    }

    private void setupTypeList()
    {
        List<String> typeList = new ArrayList<>();
        typeList.add("Food");
        typeList.add("Bills");
        typeList.add("Travel");
        typeList.add("Home");
        typeList.add("Entertainment");
        typeList.add("Leisure");
        typeList.add("Shopping");
        typeList.add("Clothing");
        typeList.add("Insurance");
        typeList.add("Tax");
        typeList.add("Healthcare");
        typeList.add("Gift");
        typeList.add("Other");

        String fileName = "expenseTypeList.txt";
        FileManagement fileManagement = new FileManagement();
        if (!fileManagement.fileExists(fileName)) {
            fileManagement.WriteListToFile(typeList, fileName, this);
        }
        //List<String> newTypeList = fileManagement.ReadListFromFile(fileName,this);
    }
}