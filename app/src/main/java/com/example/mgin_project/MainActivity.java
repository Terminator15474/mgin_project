package com.example.mgin_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mgin_project.Model.Weekday;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void initWeekDay(int id, Weekday weekday) {
        LinearLayout layout = findViewById(id);
        ListView lv = (ListView) layout.getChildAt(2);
        MedicineAdapter madp = new MedicineAdapter(weekday.getMedicines(), getLayoutInflater();
        lv.setAdapter(madp);
    }

}