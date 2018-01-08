package com.example.sergi.beastbeers20;

/**
 * Created by Sarias on 02/01/2018.
 */

public class Categories {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name + " ("+ id+ ")\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
