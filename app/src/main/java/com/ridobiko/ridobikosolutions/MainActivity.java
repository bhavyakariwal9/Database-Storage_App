package com.ridobiko.ridobikosolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText etName,etMobile,etAddress,etAadhar;
    Button btnSubmit,btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);

        etName=findViewById(R.id.etName);
        etMobile=findViewById(R.id.etMobile);
        etAddress=findViewById(R.id.etAddress);
        etAadhar=findViewById(R.id.etAadhar);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnViewData=findViewById(R.id.btnViewData);
        AddData();
        GoToAct();
    }

    public void GoToAct(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, com.ridobiko.ridobikosolutions.ViewDataEntry.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=myDb.addData(etName.getText().toString(),Long.parseLong(etMobile.getText().toString()),
                        etAddress.getText().toString(),Long.parseLong(etAadhar.getText().toString()));
                if(isInserted==true) {
                    Toast.makeText(MainActivity.this, "Data is inserted successfully", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etMobile.setText("");
                    etAddress.setText("");
                    etAadhar.setText("");
                }
                else
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });
    }

}