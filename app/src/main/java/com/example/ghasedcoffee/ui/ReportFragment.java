package com.example.ghasedcoffee.ui;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.example.ghasedcoffee.Adapter.FinancialRecyclerAdapter;
import com.example.ghasedcoffee.Adapter.RecyclerAdapter;
import com.example.ghasedcoffee.Adapter.StoreRecyclerAdapter;
import com.example.ghasedcoffee.MainActivity;
import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.databinding.FragmentReportBinding;

import java.util.ArrayList;

public class ReportFragment extends Fragment {


    private FragmentReportBinding binding;
//    private Context context = this;
    //adapter
    private RecyclerAdapter adapter;
    private StoreRecyclerAdapter storeRecyclerAdapter;
    private FinancialRecyclerAdapter financialRecyclerAdapter;
    //list
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Financial> financials = new ArrayList<>();
    private ArrayList<Store> stores = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setData();

        //RecyclerView
        adapter = new RecyclerAdapter(users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);

        storeRecyclerAdapter = new StoreRecyclerAdapter(stores);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL , false);
        binding.storeRecycler.setAdapter(storeRecyclerAdapter);
        binding.storeRecycler.setLayoutManager(layoutManager1);
        binding.storeRecycler.setHasFixedSize(true);

        financialRecyclerAdapter = new FinancialRecyclerAdapter(financials);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL , false);
        binding.financialRecycler.setAdapter(financialRecyclerAdapter);
        binding.financialRecycler.setLayoutManager(layoutManager2);
        binding.financialRecycler.setHasFixedSize(true);

        //click listeners
        binding.fab.setOnClickListener(view -> toggle(binding.dateLay));

        binding.finencialFab.setOnClickListener(v -> {
            binding.recycler.setVisibility(View.GONE);
            binding.storeRecycler.setVisibility(View.GONE);
            binding.financialRecycler.setVisibility(View.VISIBLE);
        });
        binding.storeFab.setOnClickListener(v -> {
            binding.recycler.setVisibility(View.GONE);
            binding.storeRecycler.setVisibility(View.VISIBLE);
            binding.financialRecycler.setVisibility(View.GONE);
        });
        binding.userFab.setOnClickListener(v -> {
            binding.recycler.setVisibility(View.VISIBLE);
            binding.storeRecycler.setVisibility(View.GONE);
            binding.financialRecycler.setVisibility(View.GONE);
        });
        binding.searchBtn.setOnClickListener(this::searchBtnOnClick);

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void toggle(View view) {
        Transition transition = new Slide(Gravity.TOP);
        transition.setDuration(600);
        transition.addTarget(view);
        TransitionManager.beginDelayedTransition((ViewGroup) view, transition);

        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setData() {

        users = ((MainActivity)getActivity()).getUser();
        financials = ((MainActivity)getActivity()).getFinancial("","");
        stores = ((MainActivity)getActivity()).getStore();
    }

    private void searchBtnOnClick(View v) {
        if (!binding.startDay.getText().toString().isEmpty() &&
                !binding.startMonth.getText().toString().isEmpty() &&
                !binding.startYear.getText().toString().isEmpty() &&
                !binding.endtDay.getText().toString().isEmpty() &&
                !binding.endMonth.getText().toString().isEmpty() &&
                !binding.endYear.getText().toString().isEmpty()) {
            financials.clear();
            String date = binding.startYear.getText().toString() + "-" + binding.startMonth.getText().toString() + "-" + binding.startDay.getText().toString();

            financials = ((MainActivity) getActivity()).getFinancial(binding.startYear.getText().toString()+ "-"+ binding.startMonth.getText().toString() +"-"+ binding.startDay.getText().toString(), binding.endYear.getText().toString() +"-"+binding.endMonth.getText().toString()+"-"+ binding.endtDay.getText().toString());
            financialRecyclerAdapter.notifyDataSetChanged();
            Log.d("blur", "onCreateView: search object " + financials.size());
            toggle(binding.dateLay);
        } else {
            Toast.makeText(getActivity(), "تمامی فیلد های تاریخ را پر کنید", Toast.LENGTH_SHORT).show();
        }


        binding.recycler.setVisibility(View.GONE);
        binding.storeRecycler.setVisibility(View.GONE);
        binding.financialRecycler.setVisibility(View.VISIBLE);

    }
}
