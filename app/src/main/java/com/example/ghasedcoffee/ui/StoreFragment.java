package com.example.ghasedcoffee.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ghasedcoffee.MainActivity;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.databinding.FragmentStoreBinding;

public class StoreFragment extends Fragment {

    private FragmentStoreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentStoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.commoditySubmitBtn.setOnClickListener(v -> {
            if (!binding.commodityAmountEdt.getText().toString().isEmpty() && !binding.commodityNameEdt.getText().toString().isEmpty()){
                Store store = new Store();
                store.commodity = binding.commodityNameEdt.getText().toString();
                store.Amount = binding.commodityAmountEdt.getText().toString();
                if (binding.radioKilogram.isChecked()){
                    store.unit = "kilogram";
                }else if (binding.radioLiter.isChecked()){
                    store.unit = "liter";

                }else {
                    store.unit = "num";
                }
                ((MainActivity)getActivity()).insertStore(store);
                Toast.makeText(getActivity(),"محصول با موفقیت ثبت شد" , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "لطفا تمامی فیلد ها را کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}