package com.demo.recyclerdbroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.recyclerdbroom.models.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeesDatabase extends RoomDatabase {
    private static final String DB_NAME = "employees2.db";
    private static final Object LOCK = new Object();
    private static EmployeesDatabase database;

    public static EmployeesDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, EmployeesDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return database;
    }

    public abstract EmployeesDao employeesDao();
}
