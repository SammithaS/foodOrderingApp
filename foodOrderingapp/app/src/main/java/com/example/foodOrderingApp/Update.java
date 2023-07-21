package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    EditText title_input, price_input;
    Button update_button;

    String id, title, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.bill);

        price_input = findViewById(R.id.price);
        update_button = findViewById(R.id.update_button);


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Helper myDB = new Helper(Update.this);
                title = title_input.getText().toString().trim();
                price = price_input.getText().toString().trim();
                myDB.updateData(title, price);
            }
        });


    }
}

