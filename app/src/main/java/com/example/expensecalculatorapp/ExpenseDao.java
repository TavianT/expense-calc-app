package com.example.expensecalculatorapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
        @Insert (onConflict = OnConflictStrategy.IGNORE)
        void insertExpense(Expense expense);

        @Delete
        void deleteExpense(Expense expense);

        @Query("SELECT * FROM expenses")
        List<Expense> getAll();

}


