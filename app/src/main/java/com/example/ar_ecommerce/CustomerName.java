package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Timestamp;

public class CustomerName extends AppCompatActivity{
    Button submit;
    EditText name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_name);
        submit=findViewById(R.id.submit);
        name=findViewById(R.id.bill);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time=(int)(System.currentTimeMillis());
                Timestamp ts=new Timestamp(time);
                int price=0;
                String OrderId=ts.toString().trim();
                String n=name.getText().toString().trim();

                Helper myDB = new Helper(CustomerName.this);
                myDB.addcustomer(OrderId,n,price);
                Intent i=new Intent(getApplicationContext(),Menu.class);
                startActivity(i);
            }
        });


    }
}