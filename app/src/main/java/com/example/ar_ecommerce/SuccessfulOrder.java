package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulOrder extends AppCompatActivity {
Button more,tq;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_order);
        more=findViewById(R.id.more);
        tq=findViewById(R.id.tq);
        Intent intent=getIntent();
        String Name=intent.getStringExtra("name");
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Menu.class);
                startActivity(i);
            }
        });
        tq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper myDB = new Helper(SuccessfulOrder.this);
                myDB.updateAmount(Name);
                Intent i=new Intent(getApplicationContext(),BillGeneration.class);
                i.putExtra("name",Name);
                startActivity(i);
            }
        });
    }
}