package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillGeneration extends AppCompatActivity {
Button done;
TextView bill,name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_generation);
        Intent i=getIntent();
        String n=i.getStringExtra("name");
        name=findViewById(R.id.name);
        bill=findViewById(R.id.bill);
        done=findViewById(R.id.done);
        name.setText(n);
        // Replace with the desired customer name
        Helper myDB = new Helper(BillGeneration.this);

        int totalAmount = myDB.getTotalAmountByCustomerName(n.toString().trim());
        bill.setText(totalAmount+"");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}