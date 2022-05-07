package com.example.ghasedcoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.databinding.ItemsRecyclerBinding;

import java.util.ArrayList;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<User> users;
    private Context context  ;

    public RecyclerAdapter(ArrayList<User> users){

        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        ItemsRecyclerBinding v = ItemsRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.userName.setText( users.get(position).name) ;
        holder.binding.userPhone.setText(users.get(position).phone);
        holder.binding.isSeller.setText(users.get(position).isSeller ? "فروشنده": "خریدار");

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ItemsRecyclerBinding binding;
        public ViewHolder(@NonNull ItemsRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
