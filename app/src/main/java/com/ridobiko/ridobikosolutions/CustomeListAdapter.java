package com.ridobiko.ridobikosolutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomeListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Customer> arrayList;
    TextView tvName,tvMobile,tvAddress,tvAadhar;

    public CustomeListAdapter(Context context, ArrayList<Customer> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i    ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(R.layout.customadapter,null);
        tvName=view.findViewById(R.id.tvName);
        tvMobile=view.findViewById(R.id.tvMobile);
        tvAddress=view.findViewById(R.id.tvAddress);
        tvAadhar=view.findViewById(R.id.tvAadhar);

        Customer customer=arrayList.get(i);
        tvName.setText(customer.getName());
        tvMobile.setText(String.valueOf(customer.getMobile()));
        tvAddress.setText(customer.getAddress());
        tvAadhar.setText(String.valueOf(customer.getAadhar()));



        return view;
    }

    /*public void filterList(ArrayList<Customer> listData3){
        arrayList=listData3;
        notifyDataSetChanged();
    }*/
}
