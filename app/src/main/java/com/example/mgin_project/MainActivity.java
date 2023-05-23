package com.example.mgin_project;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mgin_project.Model.Medicine;
import com.example.mgin_project.Model.Weekday;
import com.example.mgin_project.Model.WeekdayDesc;
import com.example.mgin_project.Model.WeekdayDescArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;

import java.security.Permission;
import java.util.LinkedList;
import com.example.mgin_project.Model.Weekday;


public class MainActivity extends AppCompatActivity {

    private final String MEDICINES_SAVE_FILE = "SAVE_MED.save";

    private final String WEEKDAY_SAVE_KEY = "WEEKDAY_SAVE";

    private final String MEDICINES_SAVE_KEY = "MEDICINE_SAVE_KEY";
    private final String DAYS_SAVE_FILE = "SAVE_DAYS.save";

    private final String TAG = "mgin_project";

    WeekdayDesc currentWeekday;

    ListView mon;
    ListView tue;
    ListView wed;
    ListView thu;
    ListView fr;
    ListView sat;
    ListView sun;

    Weekday[] days = {new Weekday(WeekdayDesc.MON), new Weekday(WeekdayDesc.TUES), new Weekday(WeekdayDesc.WED),
                        new Weekday(WeekdayDesc.THU), new Weekday(WeekdayDesc.FRI), new Weekday(WeekdayDesc.SAT), new Weekday(WeekdayDesc.SUN)};

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
            days = tempWeekday;
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

        // Read medicine save state
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

        mon = findViewById(R.id.lv_meds_mon);
        tue = findViewById(R.id.lv_meds_tue);
        wed = findViewById(R.id.lv_meds_wed);
        thu = findViewById(R.id.lv_meds_thu);
        fr = findViewById(R.id.lv_meds_fri);
        sat = findViewById(R.id.lv_meds_sat);
        sun = findViewById(R.id.lv_meds_sun);

        ArrayAdapter<Medicine> monAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[0].getMedicines());
        ArrayAdapter<Medicine> tueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[1].getMedicines());
        ArrayAdapter<Medicine> wedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[2].getMedicines());
        ArrayAdapter<Medicine> thuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[3].getMedicines());
        ArrayAdapter<Medicine> friAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[4].getMedicines());
        ArrayAdapter<Medicine> satAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[5].getMedicines());
        ArrayAdapter<Medicine> sunAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days[6].getMedicines());

        mon.setAdapter(monAdapter);
        tue.setAdapter(tueAdapter);
        wed.setAdapter(wedAdapter);
        thu.setAdapter(thuAdapter);
        fr.setAdapter(friAdapter);
        sat.setAdapter(satAdapter);
        sun.setAdapter(sunAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(WEEKDAY_SAVE_KEY, days);
        outState.putSerializable(MEDICINES_SAVE_KEY, allMedicines);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        days = (Weekday[]) savedInstanceState.get(WEEKDAY_SAVE_KEY);
        allMedicines = (LinkedList<Medicine>) savedInstanceState.get(MEDICINES_SAVE_KEY);

        // Write to internal storage
        try {
            FileOutputStream fos = openFileOutput(DAYS_SAVE_FILE, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(days);
            oos.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Log out file not found");
        } catch (IOException e) {
            Log.e(TAG, "Problem with ObjectOutputStream");
        }

        try {
            FileOutputStream fos = openFileOutput(MEDICINES_SAVE_FILE, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allMedicines);
            oos.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Log out file not found");
        } catch (IOException e) {
            Log.e(TAG, "Problem with ObjectOutputStream");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initWeekDay(int id, Weekday weekday) {
        LinearLayout layout = findViewById(id);
        ListView lv = (ListView) layout.getChildAt(2);
        MedicineAdapter madp = new MedicineAdapter(weekday.getMedicines(), getLayoutInflater());
        lv.setAdapter(madp);
    }

}