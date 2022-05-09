package com.example.ghasedcoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghasedcoffee.MainActivity;
import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.databinding.ItemStoreRecyclerBinding;
import com.example.ghasedcoffee.databinding.ItemsFinencialRecyclerBinding;

import java.util.ArrayList;

public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.ViewHolder> {

    private ArrayList<Store> stores;
    private Context context  ;
    private MainActivity activity;
    public StoreRecyclerAdapter(ArrayList<Store> stores , MainActivity activity){
    this.activity = activity;
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
        holder.binding.changeBtn.setOnClickListener(view -> {
            holder.binding.changeView.setVisibility(View.VISIBLE);
            holder.binding.changeBtn.setVisibility(View.GONE);

        });
        holder.binding.SubmitBtn.setOnClickListener(v -> {
            if (holder.binding.numEdt.getText().toString().isEmpty()){
                Toast.makeText(context,"لطفا فیلد ها را کامل کنید" , Toast.LENGTH_SHORT).show();
            }else{
                activity.updateStore(holder.binding.numEdt.getText().toString(),stores.get(position).id);
                holder.binding.changeBtn.setVisibility(View.VISIBLE);
                holder.binding.changeView.setVisibility(View.GONE);
                Toast.makeText(context,"مقدار وارد شد" , Toast.LENGTH_SHORT).show();
            }
        });



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
