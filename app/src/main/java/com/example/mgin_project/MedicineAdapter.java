package com.example.mgin_project;

import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mgin_project.Model.Medicine;

import java.util.LinkedList;

public class MedicineAdapter extends BaseAdapter {

    LinkedList<Medicine> internalMedList = new LinkedList<>();
    LayoutInflater inflater;

    public MedicineAdapter(LinkedList<Medicine> internalMedList, LayoutInflater inflater) {
        this.internalMedList = internalMedList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return internalMedList.size();
    }

    @Override
    public Object getItem(int i) {
        return internalMedList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return internalMedList.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.medicine_layout, null, false);
        TextView tv_name = view.findViewById(R.id.tv_medicine_name);
        TextView tv_quantity = view.findViewById(R.id.tv_medicine_quantity);

        tv_name.setText(internalMedList.get(i).getName());
        tv_quantity.setText(String.valueOf(internalMedList.get(i).getQuantity()));
        return view;
    }
}
