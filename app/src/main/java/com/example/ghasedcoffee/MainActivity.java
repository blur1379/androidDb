package com.example.ghasedcoffee;

import android.os.Bundle;
import android.util.Log;

import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.Model.User;
import com.example.ghasedcoffee.dbHelper.DatabaseManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ghasedcoffee.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public DatabaseManager dbm;
    private String todayDate = "";

    private int todayYear = 0;
    private int todayMonth = 0;
    private int todayDay = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_user, R.id.navigation_financial, R.id.navigation_store , R.id.navigation_report)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        dbm = new DatabaseManager(this);



    }
// metods for database
    public void insertUser(User user){
        dbm.insertUser(user);
    }
    public ArrayList<User> getUser(){
        return dbm.getUsers();
    }
    public void  insertFinancial(Financial financial){
        Date c = Calendar.getInstance().getTime(); //Sat Apr 09 11:29:58 GMT+04:30 2022

        SimpleDateFormat dfYear = new SimpleDateFormat("yyyy", Locale.getDefault());
        todayYear = Integer.parseInt(dfYear.format(c)); // 2020

        SimpleDateFormat dfMonth = new SimpleDateFormat("M", Locale.getDefault());
        todayMonth = Integer.parseInt(dfMonth.format(c)); // 4

        SimpleDateFormat dfDay = new SimpleDateFormat("d", Locale.getDefault());
        todayDay = Integer.parseInt(dfDay.format(c)); // 9
        Log.d("blur", "onCreate date: " + todayDate);
        financial.day = String.valueOf(todayDay);
        financial.month = String.valueOf(todayMonth);
        financial.year = String.valueOf(todayYear);
        dbm.insertFinancial(financial);
    }

    public ArrayList<Financial> getFinancial(String startDay , String startMonth , String startYear , String endDay , String endMonth , String endYear ){
        return dbm.getFinancial(startDay , startMonth , startYear , endDay , endMonth , endYear);
    }
    public void insertStore(Store store){
        dbm.insertStore(store);
    }
    public ArrayList<Store> getStore(){
        return dbm.getStore();
    }

}