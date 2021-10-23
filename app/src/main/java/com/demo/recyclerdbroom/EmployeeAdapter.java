package com.demo.recyclerdbroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.recyclerdbroom.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    List<Employee> employees;
    private OnEmployeeClickListener onEmployeeClickListener;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;

    }

//    public void addEmployee(Employee employee) {
//        employees.add(employee);
//        notifyItemInserted(employees.size() - 1);
//        notifyDataSetChanged();
//    }

    public void setOnEmployeeClickListener(OnEmployeeClickListener onEmployeeClickListener) {
        this.onEmployeeClickListener = onEmployeeClickListener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.onBind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    interface OnEmployeeClickListener {
        void onEmployeeClick(int position);
        void onLongClick(int position);
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView name, surnme, job;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            surnme = itemView.findViewById(R.id.textViewSurname);
            job = itemView.findViewById(R.id.textViewJob);
            itemView.setOnClickListener(view -> {
                if (onEmployeeClickListener != null) {
                    onEmployeeClickListener.onEmployeeClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(view -> {
                if (onEmployeeClickListener != null) {
                    onEmployeeClickListener.onLongClick(getAdapterPosition());
                }
                return true;
            });
        }

        private void onBind(Employee employee) {
            name.setText(employee.getName());
            surnme.setText(employee.getSurname());
            job.setText(employee.getJob());
        }
    }
}
