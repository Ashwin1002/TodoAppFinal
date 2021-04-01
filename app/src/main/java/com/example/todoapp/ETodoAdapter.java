package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.model.ETodo;

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ETodo task = data.get(position);
        holder.bind(task);
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

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_todo, parent, false));
            titleTextView = itemView.findViewById(R.id.title_tv);
            descTextView = itemView.findViewById(R.id.descripttion_tv);
        }

        public void bind(ETodo task) {
            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());
        }
    }
}
