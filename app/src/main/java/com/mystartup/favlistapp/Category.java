package com.mystartup.favlistapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {


    private String name;
    private ArrayList<String> items = new ArrayList<>();

    public Category(String name, ArrayList<String> items) {

        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return items;
    }

}
