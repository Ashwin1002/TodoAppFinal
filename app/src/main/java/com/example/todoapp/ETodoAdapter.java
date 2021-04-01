package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.model.ETodo;

import java.util.ArrayList;
import java.util.List;

public class ETodoAdapter  extends RecyclerView.Adapter<ETodoAdapter.ViewHolder> {

    private List<ETodo> data;
    public ETodoAdapter(LiveData<List<ETodo>> tasks) {

    }

    public void setData(List<ETodo> tasks){
        data = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ETodo eTodo = data.get(position);

        holder.titleTextView.setText(eTodo.getTitle());
        holder.titleTextView.setText(eTodo.getDescription());
    }

    @Override
    public int getItemCount() {

        if(data == null)
            return  0;
            return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView descTextView;

        public ViewHolder(@NonNull View view) {
            super(view);
            titleTextView = view.findViewById(R.id.title_tv);
            descTextView = view.findViewById(R.id.descripttion_tv);
        }

        public void bind(ETodo task) {
            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());
        }
    }
}
