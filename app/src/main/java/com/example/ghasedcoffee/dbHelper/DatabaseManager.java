package com.example.ghasedcoffee.dbHelper;

import android.annotation.SuppressLint;
import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ghasedcoffee.Model.Financial;
import com.example.ghasedcoffee.Model.Store;
import com.example.ghasedcoffee.Model.User;

import java.util.ArrayList;
import java.util.Currency;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DatabaseName  = "ghasedCoffee.db" ;
    public static final int Version = 1 ;

    // user table
    public static final String UserTableName  = "tbl_user" ;
    public static final String userID  = "id" ;
    public static final String userName  = "name" ;
    public static final String userPhone  = "phone" ;
    public static final String userIsSeller = "isSeller";
    public static final String userDebt = "debt";
    public static final String userIsCreditor = "isCreditor";
    // store table
    public static final String StoreTableName = "tbl_store";
    public static final String storeID = "id";
    public static final String storeCommodity = "commodity";
    public static final String storeUnit = "unit";
    public static final String storeAmount = "amount";
    // financial table
    public static final String FinancialTableName = "tbl_financial";
    public static final String financialID = "id";
    public static final String financialCredit = "credit";
    public static final String financialType = "type";
    public static final String financialDate = "date";
    public DatabaseManager(@Nullable Context context) {
        super(context, DatabaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userQuery = "CREATE TABLE "+ UserTableName +" ("+ userID +" INTEGER PRIMARY KEY, "+ userName +" VARCHAR , "+ userPhone +" TEXT ,"+ userIsSeller +" BOOLEAN ,"+ userDebt +" INTEGER ," + userIsCreditor+ " BOOLEAN ); ";
        String storeQuery = "CREATE TABLE "+ StoreTableName +" ("+ storeID +" INTEGER PRIMARY KEY, "+ storeAmount +" DOUBLE , "+ storeUnit +" TEXT ," + storeCommodity + " VARCHAR ); ";
        String financialQuery = "CREATE TABLE "+ FinancialTableName +" ("+ financialID +" INTEGER PRIMARY KEY, "+ financialCredit +" INTEGER , "+ financialType +" TEXT ," + financialDate +" TEXT); ";
        db.execSQL(userQuery);
        db.execSQL(storeQuery);
        db.execSQL(financialQuery);
        Log.d("blur", "onCreate: database ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert models
    public void insertUser (User user){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(userID , user.id);
        icv.put(userName , user.name);
        icv.put(userPhone, user.phone);
        icv.put(userIsSeller, user.isSeller);
        icv.put(userDebt, user.debt);
        icv.put(userIsCreditor, user.isCreditor);
        idb.insert(UserTableName,null,icv);
        idb.close();
        Log.d("blur", "insert user ");
    }
    public void insertStore (Store store){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(storeID , store.id);
        icv.put(storeAmount , store.Amount);
        icv.put(storeCommodity, store.commodity);
        icv.put(storeUnit, store.unit);
        idb.insert(StoreTableName,null,icv);

        idb.close();
    }
    public void insertFinancial (Financial financial){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(financialID , financial.id);
        icv.put(financialCredit , financial.credit);
        icv.put(financialType, financial.type);
        icv.put(financialDate, financial.date);

        idb.insert(FinancialTableName,null,icv);
        idb.close();
    }

    // get models

    @SuppressLint("Range")
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery  = "SELECT * FROM "+ UserTableName +";";
        Cursor gCur = gdb.rawQuery(gQuery,null);
        gCur.moveToFirst();
        while(!gCur.isAfterLast()) {
            User user = new User();
            user.id = gCur.getString(gCur.getColumnIndex(userID));
            user.name = gCur.getString(gCur.getColumnIndex(userName));
            user.phone = gCur.getString(gCur.getColumnIndex(userPhone));
            user.debt = gCur.getString(gCur.getColumnIndex(userDebt));
            user.isSeller = gCur.getInt(gCur.getColumnIndex(userIsSeller)) > 0 ;
            user.isCreditor= gCur.getInt(gCur.getColumnIndex(userIsCreditor)) > 0 ;
            Log.d("blurUser", "getUsers: " + " " + user.id + " " + user.name + " " + user.phone );

            users.add(user);
            gCur.moveToNext();
        }
        return users;
    }
    @SuppressLint("Range")
    public ArrayList<Store> getStore(){
        ArrayList<Store> stores = new ArrayList<>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery  = "SELECT * FROM "+ StoreTableName +";";
        Cursor gCur = gdb.rawQuery(gQuery,null);
        gCur.moveToFirst();
        while(!gCur.isAfterLast()) {
            Store store = new Store();
            store.id = gCur.getString(gCur.getColumnIndex(storeID));
            store.commodity = gCur.getString(gCur.getColumnIndex(storeCommodity));
            store.unit = gCur.getString(gCur.getColumnIndex(storeUnit));
            store.Amount = gCur.getString(gCur.getColumnIndex(storeAmount)) ;
            stores.add(store);
            gCur.moveToNext();
        }
        return stores;
    }
    @SuppressLint("Range")
    public ArrayList<Financial> getFinancial(String startDate , String endDate ){
        ArrayList<Financial> financials = new ArrayList<>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM "+ FinancialTableName ;
        if (!startDate.isEmpty() && !endDate.isEmpty()){
                gQuery  += " WHERE "+ financialDate +" BETWEEN '"+ startDate +"' AND '"+endDate+"'";
        }
        gQuery += ";";


        Cursor gCur = gdb.rawQuery(gQuery,null);
        gCur.moveToFirst();
        while(!gCur.isAfterLast()) {
            Financial financial = new Financial();
            financial.id = gCur.getString(gCur.getColumnIndex(financialID));
            financial.credit = gCur.getString(gCur.getColumnIndex(financialCredit));
            financial.type = gCur.getString(gCur.getColumnIndex(financialType));
            financial.date = gCur.getString(gCur.getColumnIndex(financialDate));
            Log.d("blur", "getFinancialobj: " + financial.id);
            financials.add(financial);

            gCur.moveToNext();
        }
        return financials;
    }

    public void updateUser(String debt , Boolean isCreditor , String id){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(userDebt, debt);
        icv.put(userIsCreditor, isCreditor);
        idb.update(UserTableName,icv,"id = ?",new String[]{id});
        idb.close();
        Log.d("blur", "update user ");
    }
    public void updateStore(String debt , String id){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(storeAmount, debt);
        idb.update(StoreTableName,icv,"id = ?",new String[]{id});
        idb.close();
        Log.d("blur", "insert user ");
    }

}
