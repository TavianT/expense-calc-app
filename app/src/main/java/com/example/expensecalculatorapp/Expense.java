package com.example.expensecalculatorapp;

import androidx.room.*;

import java.time.LocalDate;

@Entity(tableName = "Expenses")
public class Expense {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "name")
    public String expenseName;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "amount")
    public double amount;

    @ColumnInfo(name = "date")
    public LocalDate date;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "account")
    public String account = "";

    @ColumnInfo(name = "memo")
    public String memo;

    Expense(String expenseName, String category, double amount, LocalDate date, String type, String account, String memo)
    {
        this.expenseName = expenseName;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.account = account;
        this.memo = memo;
    }

}
