package com.demo.recyclerdbroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.demo.recyclerdbroom.databinding.ActivityMainBinding;
import com.demo.recyclerdbroom.models.Employee;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<Employee> employees = new ArrayList<>();
    private EmployeeAdapter employeeAdapter;
    private EmployeesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = EmployeesDatabase.getInstance(this);

        binding.recyclerView.setAdapter(employeeAdapter = new EmployeeAdapter(employees));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();

        employeeAdapter.setOnEmployeeClickListener(new EmployeeAdapter.OnEmployeeClickListener() {
            @Override
            public void onEmployeeClick(int position) {

            }

            @Override
            public void onLongClick(int position) {
                remove(position);
            }
        });

        binding.buttonSave.setOnClickListener(view -> {
            database.employeesDao().insertEmployee(new Employee(binding.editTextName.getText().toString(),
                    binding.editTextSurname.getText().toString(),
                    binding.editTextJob.getText().toString()));
            getData();

        });

//        binding.buttonSave.setOnClickListener(view -> {
//            employeeAdapter.addEmployee(new Employee(binding.editTextName.getText().toString(),
//                    binding.editTextSurname.getText().toString(),
//                    binding.editTextJob.getText().toString()));
//        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @
                    NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
    }

    private void remove(int position) {
        Employee employee = employees.get(position);
//        employees.remove(position);
        database.employeesDao().deleteEmployee(employee);
        getData();
        employeeAdapter.notifyDataSetChanged();
    }

    private void getData() {
        List<Employee> employeesFromDB = database.employeesDao().getAllEmployees();
        employees.clear();
        employees.addAll(employeesFromDB);
        employeeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}