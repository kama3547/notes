package com.example.noteapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.model.TaskModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<TaskModel> list =new ArrayList<>();

    public void addModel(TaskModel model){
        list.add(model);
        notifyDataSetChanged();
    }
    public void setList(List<TaskModel>models){
        this.list.addAll(models);
        notifyDataSetChanged();
    }
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
        }
        public void onBind(TaskModel taskModel){
            title.setText(taskModel.getTitle());
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<TaskModel> filterList){
        list = filterList;
        notifyDataSetChanged();
    }
}


