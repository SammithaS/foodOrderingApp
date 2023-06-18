package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FailedPayment extends AppCompatActivity {
Button tryagain;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_payment);
        tryagain=findViewById(R.id.tryagain);
        Intent intent=getIntent();
        String n= intent.getStringExtra("name");
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),BillGeneration.class);
                i.putExtra("name",n);
                startActivity(i);
            }
        });
    }
}