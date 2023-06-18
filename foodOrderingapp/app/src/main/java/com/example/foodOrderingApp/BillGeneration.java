package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class BillGeneration extends AppCompatActivity implements PaymentResultListener {
Button done;
int totalAmount;
String n;
TextView bill,name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_generation);
        Checkout.preload(getApplicationContext());

        Intent i=getIntent();
        n=i.getStringExtra("name");
        name=findViewById(R.id.name);
        bill=findViewById(R.id.bill);
        done=findViewById(R.id.done);
        name.setText(n);
        // Replace with the desired customer name
        Helper myDB = new Helper(BillGeneration.this);

        totalAmount = myDB.getTotalAmountByCustomerName(n.toString().trim());
        bill.setText(totalAmount+"");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });
    }

    private void makepayment() {
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_live_txUKWMusInxxlm");
        checkout.setImage(R.drawable.onlineshopping);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Foodie Tummy");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", (totalAmount*100));//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Intent i=new Intent(getApplicationContext(),SuccessfulPayment.class);
        startActivity(i);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Intent i1=new Intent(getApplicationContext(),FailedPayment.class);
        i1.putExtra("name",n);
        startActivity(i1);
    }
}