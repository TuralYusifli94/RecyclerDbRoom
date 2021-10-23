package com.demo.recyclerdbroom.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "employees")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;
    private String job;

    public Employee(int id, String name, String surname, String job) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    @Ignore
    public Employee(String name, String surname, String job) {
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
