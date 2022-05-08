package com.example.ghasedcoffee.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ghasedcoffee.MainActivity;
import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.userSubmitBtn.setOnClickListener(v -> {
            if (!binding.userNameEdt.getText().toString().isEmpty() && !binding.userPhoneEdt.getText().toString().isEmpty() && !binding.financialEdt.getText().toString().isEmpty()){
                User user = new User();
                user.name = binding.userNameEdt.getText().toString();
                user.phone = binding.userPhoneEdt.getText().toString();
                user.isSeller = binding.radioSeller.isChecked();
                Log.d("blur", "onCreateView: " + binding.radioSeller.isChecked());
                ((MainActivity)getActivity()).insertUser(user);
                Toast.makeText(getActivity(),"کاربر ثبت شد", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"تمامی فیلد ها را تکمیل کنید", Toast.LENGTH_SHORT).show();
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