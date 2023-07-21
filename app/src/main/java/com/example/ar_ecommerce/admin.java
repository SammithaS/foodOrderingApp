package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class admin extends AppCompatActivity {
Button trackorder,addFoodItem,update,foodlist;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        trackorder=findViewById(R.id.editButton2);
        update=findViewById(R.id.editButton1);
        addFoodItem=findViewById(R.id.editButton);
        foodlist=findViewById(R.id.foodlist);
        trackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TrackOrder.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Update.class);
                startActivity(intent);
            }
        });
        addFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddItem.class);
                startActivity(intent);
            }
        });
        foodlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Menu.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(admin.this, MainActivity.class);
        startActivity(intent);
    }
}