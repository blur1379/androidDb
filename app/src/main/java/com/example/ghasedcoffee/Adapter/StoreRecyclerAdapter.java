package com.example.ghasedcoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.databinding.ItemStoreRecyclerBinding;
import com.example.ghasedcoffee.databinding.ItemsFinencialRecyclerBinding;

import java.util.ArrayList;

public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.ViewHolder> {

    private ArrayList<Store> stores;
    private Context context  ;

    public StoreRecyclerAdapter(ArrayList<Store> stores){

        this.stores = stores;
    }

    @NonNull
    @Override
    public StoreRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        ItemStoreRecyclerBinding v = ItemStoreRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StoreRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreRecyclerAdapter.ViewHolder holder, int position) {

        holder.binding.storeCommodity.setText( stores.get(position).commodity) ;
        holder.binding.storeUnit.setText(stores.get(position).unit);
        holder.binding.storeNumber.setText(stores.get(position).Amount);

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ItemStoreRecyclerBinding binding;
        public ViewHolder(@NonNull ItemStoreRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
