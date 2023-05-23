package com.example.mgin_project.Model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

public class Weekday implements Serializable {
    WeekdayDesc weekday;
    LinkedList<Medicine> medicines;

    public Weekday(WeekdayDesc weekday, Medicine[] medicines) {
        this.weekday = weekday;
        this.medicines.addAll(Arrays.asList(medicines));
    }

    public Weekday(WeekdayDesc weekday) {
        this.weekday = weekday;
    }

    public void addMedicine(Medicine med) {
        medicines.add(med);
    }

    public boolean removeMedicine(Medicine med) {
        return medicines.remove(med);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Weekday)) return false;
        if(((Weekday) obj).weekday == this.weekday) return true;
        return false;
    }

    public LinkedList<Medicine> getMedicines() {
        return medicines;
    }
}
