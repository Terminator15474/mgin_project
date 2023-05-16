package com.example.mgin_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;

import java.security.Permission;

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

    }

}