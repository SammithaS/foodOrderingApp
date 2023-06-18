package com.example.foodOrderingApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackOrder extends AppCompatActivity {
    RecyclerView recyclerView;

    ImageView empty_imageview;
    TextView no_data;


    Helper myDB;
    ArrayList<String> food_id, item, price;
    CustomAdapter2 customAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        recyclerView = findViewById(R.id.recyclerView1);

        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        myDB = new Helper(TrackOrder.this);
        food_id = new ArrayList<>();
        item = new ArrayList<>();
        price = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter2(TrackOrder.this,getApplicationContext(),food_id,item,
                price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrackOrder.this));
        Intent i=getIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData1();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                food_id.add(cursor.getString(0));
                item.add(cursor.getString(1));
                price.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrackOrder.this, admin.class);

        startActivity(intent);
    }
}