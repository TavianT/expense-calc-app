package com.example.expensecalculatorapp;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    public boolean fileExists(String fileName)
    {
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
        return false;
    }
    public void WriteListToFile(List<String> list, String fileName, Context context)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            for (String item : list) {
                outputStreamWriter.write(item);
                outputStreamWriter.write("\n");
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public List<String> ReadListFromFile(String fileName, Context context)
    {
        List<String> typeList = new ArrayList<>();
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while(line != null) {
                    typeList.add(line);
                    line = bufferedReader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("Exception", "File not found: " + e.toString());
        } catch(IOException e) {
            Log.e("Exception", "Can not read file: " + e.toString());
        }
        return  typeList;
    }
}
