package com.ridobiko.ridobikosolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDataEntry extends AppCompatActivity{
    public String SearchMobData;
    DatabaseHelper myDb;
    private ListView listView;
    public EditText etSearchMobile;
    Button btnSearch;
    //TextView tvName,tvMobile,tvAddress,tvAadhar;
    CustomeListAdapter customeAdapter;
    ArrayList<Customer> arrayList;
    Context context1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_entry);
        Intent intent=getIntent();

        listView=findViewById(R.id.list_view);
        myDb=new DatabaseHelper(this);
        arrayList=new ArrayList<>();
        etSearchMobile=findViewById(R.id.etSearchMobile);
        btnSearch=findViewById(R.id.btnSearch);
        SearchMobData=etSearchMobile.getText().toString();

        //populateListView();
        populateListView2();
        //onMobileSearch();
        onMobileSearch2();

    }

    private void onMobileSearch2() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etSearchMobile.getText().toString().isEmpty()) {
                try{
                    ifEmptyMessage();
                }
                catch (NullPointerException ignored) {
                }
                    //Toast.makeText(context1,"Enter Something!",Toast.LENGTH_LONG).show();
                    //populateListView2();
                }
                else {
                    /*arrayList = getAllFilteredData();
                    customeAdapter = new CustomeListAdapter(ViewDataEntry.this, arrayList);
                    listView.setAdapter(customeAdapter);
                    customeAdapter.notifyDataSetChanged();*/
                    populateListView3();
                }
            }
        });

    }

    private void populateListView2() {
            arrayList = myDb.getAllData();
            customeAdapter = new CustomeListAdapter(this, arrayList);
            listView.setAdapter(customeAdapter);
            customeAdapter.notifyDataSetChanged();
    }

    private void populateListView3() {
        arrayList = getAllFilteredData();
        customeAdapter = new CustomeListAdapter(this, arrayList);
        listView.setAdapter(customeAdapter);
        customeAdapter.notifyDataSetChanged();
    }


    public ArrayList<Customer> getAllFilteredData(){
        final String SEARCH_MOBILE=etSearchMobile.getText().toString();
        SQLiteDatabase db=this.myDb.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+myDb.TABLE_NAME+" WHERE CUSTOMER_MOBILE='"+SEARCH_MOBILE+"'",null);
        ArrayList<Customer> listData2=new ArrayList<>();

        while(res.moveToNext()){
            String name=res.getString(1);
            Long mobile=res.getLong(2);
            String address=res.getString(3);
            Long aadhar=res.getLong(4);
            Customer customer=new Customer(name,mobile,address,aadhar);

            listData2.add(customer);
        }
        return listData2;
    }

    private void ifEmptyMessage(){
        Toast.makeText(this,"Enter Something!",Toast.LENGTH_LONG).show();
    }

    /* Scraps
    private void populateListView() {

        Cursor res=myDb.getData();
        ArrayList<String> listData=new ArrayList<>();
        while(res.moveToNext()){
            listData.add(res.getString(1));
            listData.add(res.getString(2));
            listData.add(res.getString(3));
            listData.add(res.getString(4));
        }
        ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }*/


    /*Scraps
    private void onMobileSearch() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int mob=Integer.parseInt(etSearchMobile.getText().toString());
                if(etSearchMobile.getText().toString()==null){
                    Toast.makeText(ViewDataEntry.this,"Enter mobile number to search",Toast.LENGTH_LONG).show();
                    populateListView2();
                }
                else{
                    arrayList = myDb.getAllFilteredData();
                    customeAdapter = new CustomeListAdapter(ViewDataEntry.this, arrayList);
                    listView.setAdapter(customeAdapter);
                    customeAdapter.notifyDataSetChanged();
                }
            }
        });
    }*/


}