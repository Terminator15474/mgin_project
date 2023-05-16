package com.example.mgin_project.Model;

import java.io.Serializable;

public class Medicine implements Serializable {
    private String name;
    private int quantity;

    public Medicine(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
