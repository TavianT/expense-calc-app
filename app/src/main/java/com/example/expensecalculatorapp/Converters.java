package com.example.expensecalculatorapp;

import android.util.Log;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters
{
    @TypeConverter
    public static LocalDate fromString(String dateString)
    {
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    @TypeConverter
    public static String localDateToString(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Log.d("Date to String converter", "localDateToString: " + dateString);
        return dateString;
    }
}
