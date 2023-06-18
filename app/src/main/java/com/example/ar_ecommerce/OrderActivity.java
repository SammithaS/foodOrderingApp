package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
TextView item,quantity,total;
EditText name;
int count=1,totalp=0;
Button order,plus,minus;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        item=findViewById(R.id.item);
        quantity=findViewById(R.id.quantity);
        total=findViewById(R.id.totalprice);
        order=findViewById(R.id.button);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
        name=findViewById(R.id.name2);
        Intent i=getIntent();
        String itemName=i.getStringExtra("item");
        String id=i.getStringExtra("id");
        String price=i.getStringExtra("price");
        item.setText(itemName);
        total.setText(price);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count++;
                totalp=Integer.valueOf(price)*count;
                quantity.setText(count+"");
                total.setText(totalp+"");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>1){

                count--;
                totalp=Integer.valueOf(price)*count;
                quantity.setText(count+"");
                total.setText(totalp+"");}

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SuccessfulOrder.class);
                i.putExtra("name",name.getText().toString().trim());
                startActivity(i);
                Helper myDB = new Helper(OrderActivity.this);
                myDB.CustomerFoodrecords(Integer.valueOf(id),name.getText().toString().trim(),count);
            }
        });

    }
}