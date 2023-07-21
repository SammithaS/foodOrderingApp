package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    EditText title_input, author_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        title_input = findViewById(R.id.bill);
        price_input = findViewById(R.id.price);
        add_button = findViewById(R.id.done);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper myDB = new Helper(AddItem.this);
                myDB.addFood(title_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));
            }
        });
    }
}