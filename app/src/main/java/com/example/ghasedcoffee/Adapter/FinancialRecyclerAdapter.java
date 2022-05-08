package com.example.ghasedcoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.databinding.ItemsFinencialRecyclerBinding;
import com.example.ghasedcoffee.databinding.ItemsRecyclerBinding;

import java.util.ArrayList;

public class FinancialRecyclerAdapter extends RecyclerView.Adapter<FinancialRecyclerAdapter.ViewHolder> {

    private ArrayList<Financial> financials;
    private Context context  ;

    public FinancialRecyclerAdapter(ArrayList<Financial> financials){

        this.financials = financials;
    }

    @NonNull
    @Override
    public FinancialRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        ItemsFinencialRecyclerBinding v = ItemsFinencialRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialRecyclerAdapter.ViewHolder holder, int position) {

        holder.binding.finencialCredit.setText( financials.get(position).credit) ;
        holder.binding.financialType.setText(financials.get(position).type);
        holder.binding.financialDate.setText(financials.get(position).date );

    }

    @Override
    public int getItemCount() {
        return financials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ItemsFinencialRecyclerBinding binding;
        public ViewHolder(@NonNull ItemsFinencialRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}