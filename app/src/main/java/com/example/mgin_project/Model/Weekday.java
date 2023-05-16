package com.example.mgin_project.Model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

public class Weekday implements Serializable {
    WeekdayDesc weekday;
    LinkedList<Medicine> dueOn;

    public Weekday(WeekdayDesc weekday, Medicine[] dueOn) {
        this.weekday = weekday;
        this.dueOn.addAll(Arrays.asList(dueOn));
    }

    public void addMedicine(Medicine med) {
        dueOn.add(med);
    }

    public boolean removeMedicine(Medicine med) {
        return dueOn.remove(med);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Weekday)) return false;
        if(((Weekday) obj).weekday == this.weekday) return true;
        return false;
    }
}
