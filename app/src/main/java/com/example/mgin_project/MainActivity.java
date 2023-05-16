package com.example.mgin_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mgin_project.Model.Medicine;
import com.example.mgin_project.Model.Weekday;
import com.example.mgin_project.Model.WeekdayDesc;
import com.example.mgin_project.Model.WeekdayDescArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Date;

import java.security.Permission;
import java.util.LinkedList;
import com.example.mgin_project.Model.Weekday;


public class MainActivity extends AppCompatActivity {

    private final String MEDICINES_SAVE_FILE = "SAVE_MED.save";
    private final String DAYS_SAVE_FILE = "SAVE_DAYS.save";

    private final String TAG = "mgin_project";

    WeekdayDesc currentWeekday;

    Weekday[] days = {new Weekday(WeekdayDesc.MON, null), new Weekday(WeekdayDesc.TUES, null), new Weekday(WeekdayDesc.WED, null),
                        new Weekday(WeekdayDesc.THU, null), new Weekday(WeekdayDesc.FRI, null), new Weekday(WeekdayDesc.SAT, null), new Weekday(WeekdayDesc.SUN, null)};

    LinkedList<Medicine> allMedicines = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date d = new Date();
        int day = d.getDay();
        currentWeekday = WeekdayDescArray.array[day];

        // Read saved state from file.
        try {
            FileInputStream saveFile = openFileInput(DAYS_SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(saveFile);
            Weekday[] tempWeekday = (Weekday[])ois.readObject();
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Save file not created, creating!");
            try {
                FileOutputStream saveFileOut = openFileOutput(DAYS_SAVE_FILE, MODE_PRIVATE);
                saveFileOut.close();
            } catch (FileNotFoundException ex) {
                Log.e(TAG, "Save file could not be created!");
            } catch (IOException ex) {
                Log.e(TAG, "Problem with closing save File");
            }
        } catch (IOException e) {
            Log.e(TAG, "Object input stream could not be created");
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "Problem with reading object in ObjectInputStream");
        }

        try {
            FileInputStream saveFile = openFileInput(MEDICINES_SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(saveFile);
            allMedicines = (LinkedList<Medicine>) ois.readObject();
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Save file not created, creating!");
            try {
                FileOutputStream saveFileOut = openFileOutput(MEDICINES_SAVE_FILE, MODE_PRIVATE);
                saveFileOut.close();
            } catch (FileNotFoundException ex) {
                Log.e(TAG, "Save file could not be created!");
            } catch (IOException ex) {
                Log.e(TAG, "Problem with closing save File");
            }
        } catch (IOException e) {
            Log.e(TAG, "Object input stream could not be created");
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "Problem with reading object in ObjectInputStream");
        }


    }

    private void initWeekDay(int id, Weekday weekday) {
        LinearLayout layout = findViewById(id);
        ListView lv = (ListView) layout.getChildAt(2);
        MedicineAdapter madp = new MedicineAdapter(weekday.getMedicines(), getLayoutInflater());
        lv.setAdapter(madp);
    }

}