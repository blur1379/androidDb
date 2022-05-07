package com.example.ghasedcoffee.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.example.ghasedcoffee.Adapter.RecyclerAdapter;
import com.example.ghasedcoffee.MainActivity;
import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.databinding.FragmentReportBinding;

import java.util.ArrayList;

public class ReportFragment extends Fragment {


    private FragmentReportBinding binding;
//    private Context context = this;
    //adapter
    private RecyclerAdapter adapter;
    //list
    private ArrayList<User> users = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        binding.textDashboard.setOnClickListener(v -> {
//            ((MainActivity)getActivity()).getUser();
//        });
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        setData();

        //RecyclerView
        adapter = new RecyclerAdapter(users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);

        //click listeners
        binding.fab.setOnClickListener(view -> toggle(binding.dateLay));


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
    }
}
