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

import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.databinding.FragmentFinancialBinding;

public class FinancialFragment extends Fragment {

    private FragmentFinancialBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFinancialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.financialSubmitBtn.setOnClickListener(v -> {
            if (!binding.inancialEdt.getText().toString().isEmpty()){
                Financial financial = new Financial();
                if (binding.radioCredit.isChecked()){
                    financial.credit = binding.inancialEdt.getText().toString();
                }else{
                    financial.credit = "-" + binding.inancialEdt.getText().toString();
                }
                if (binding.radioBank.isChecked()){
                    financial.type = "bank";
                }else if (binding.radioCash.isChecked()){
                    financial.type = "cash";
                }else{
                    financial.type = "chek";
                }
                ((MainActivity)getActivity()).insertFinancial(financial);
                Toast.makeText(getActivity(),"تراکنش با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"تمامی فیلد ها را تکمیل کنید", Toast.LENGTH_SHORT).show();
            }
        });
//        binding.textDashboard.setOnClickListener(v -> {
//            ((MainActivity)getActivity()).getUser();
//        });
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}