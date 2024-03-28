package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.example.myapplication.database.TaskDatabase;
import com.example.myapplication.database.UserDAO;

import java.util.List;
import java.util.ArrayList;
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Task> tasks;
    public Task curTask;

    public DataAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        curTask = task;
        holder.bind(task);
    }
    public void removeItem(int position) {
        if (position >= 0 && position < tasks.size()) {
            tasks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tasks.size());
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private Button detailsButton;
        private Dialog dialog;
        private Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.detail_dialog);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsButton = itemView.findViewById(R.id.detailsButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);


            // Find views in the dialog layout
            TextView descriptionTextView = dialog.findViewById(R.id.descriptionTextView);
            TextView timeTextView = dialog.findViewById(R.id.timeTextView);
            Button editButton = dialog.findViewById(R.id.editButton);


            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = curTask;
                    descriptionTextView.setText(task.getTaskDescription());
                    timeTextView.setText(task.getTimeByFormat());
                    editButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(itemView.getContext(), TaskEdit.class);
                            intent.putExtra("Task",curTask);
                            itemView.getContext().startActivity(intent);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDAO taskDao = TaskDatabase.connectDB(itemView.getContext()).userDao();
                    taskDao.deleteTask(curTask);
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        removeItem(position);
                    }
                }
            });
        }

        public void bind(Task task) {
            nameTextView.setText(task.getTaskName());
        }
    }
}