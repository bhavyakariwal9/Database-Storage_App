package com.ridobiko.ridobikosolutions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "test.db";
    public static final String TABLE_NAME= "details";

    public static final String ID= "ID";
    public static final String CUST_NAME= "CUSTOMER_NAME";
    public static final String CUST_MOB= "CUSTOMER_MOBILE";
    public static final String CUST_ADD= "CUSTOMER_ADDRESS";
    public static final String AADHAR_NUM= "AADHAR_NUMBER";
    //ViewDataEntry obj;
    //final String SEARCH_MOBILE=obj.etSearchMobile.getText().toString();


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CUSTOMER_NAME TEXT,CUSTOMER_MOBILE INTEGER,CUSTOMER_ADDRESS TEXT," +
                "AADHAR_NUMBER INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name,long number,String address,long aadhar){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(CUST_NAME,name);
        contentValues.put(CUST_MOB,number);
        contentValues.put(CUST_ADD,address);
        contentValues.put(AADHAR_NUM,aadhar);
        long res=db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)
            return  false;
        else
            return true;
    }

    /*public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }*/

    public ArrayList<Customer> getAllData(){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<Customer> listData2=new ArrayList<>();

        while(res.moveToNext()){
            String name=res.getString(1);
            long mobile=res.getLong(2);
            String address=res.getString(3);
            int aadhar=res.getInt(4);
            Customer customer=new Customer(name,mobile,address,aadhar);

            listData2.add(customer);
        }
        return listData2;
    }


    /*Scrapped because NullPointerException for etSearchMobile
    public ArrayList<Customer> getAllFilteredData(){

        SQLiteDatabase db=this.getWritableDatabase();
            final String SearchMobile=obj.SearchMobData;
            //String SearchMobile=obj.etSearchMobile.getText().toString();
            Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE CUSTOMER_MOBILE="+SearchMobile, null);
            ArrayList<Customer> listData2 = new ArrayList<>();
            while (res.moveToNext()) {
                String name = res.getString(1);
                int mobile = res.getInt(2);
                String address = res.getString(3);
                int aadhar = res.getInt(4);
                Customer customer = new Customer(name, mobile, address, aadhar);

                listData2.add(customer);
            }
            return listData2;
    }*/
}
