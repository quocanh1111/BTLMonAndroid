package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
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
    Task curTask;

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

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private Button detailsButton;
        private Dialog dialog;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            detailsButton = itemView.findViewById(R.id.detailsButton);
            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.detail_dialog);

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
                            Task editedTask = tasks.get(getAdapterPosition());
                            // Modify the editedTask with the updated values

                            // Save the edited task to the Room database
                            UserDAO taskDao = TaskDatabase.connectDB(itemView.getContext()).userDao();
                            taskDao.updateTask(editedTask);

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }

        public void bind(Task task) {
            nameTextView.setText(task.getTaskName());
        }
    }
}