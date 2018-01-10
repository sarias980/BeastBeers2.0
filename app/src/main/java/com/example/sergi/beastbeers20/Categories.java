package com.example.sergi.beastbeers20;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Sarias on 02/01/2018.
 */

@Entity
public class Categories implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private String desc;

    @Override
    public String toString() {
        return name + " (" + id + ")\n";
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
