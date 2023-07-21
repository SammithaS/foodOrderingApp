package com.example.ar_ecommerce;

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

     //   getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setTitle(title);
//        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                Helper myDB = new Helper(Update.this);
                title = title_input.getText().toString().trim();
                price = price_input.getText().toString().trim();
                myDB.updateData(title, price);
            }
        });
      

    }

//    void getAndSetIntentData(){
//        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
//                getIntent().hasExtra("author") && getIntent().hasExtra("price")){
//            //Getting Data from Intent
//            id = getIntent().getStringExtra("id");
//            title = getIntent().getStringExtra("title");
//            price = getIntent().getStringExtra("price");
//
//            //Setting Intent Data
//            title_input.setText(title);
//
//            price_input.setText(price);
//            Log.d(" ", title+" "+price);
//        }else{
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
//        }
    //}


}
//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete " + title + " ?");
//        builder.setMessage("Are you sure you want to delete " + title + " ?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                myDB.deleteOneRow(id);
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }