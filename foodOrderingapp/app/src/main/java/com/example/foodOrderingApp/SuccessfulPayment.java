package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulPayment extends AppCompatActivity {
Button home;
    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_payment);
        home=findViewById(R.id.home);
        String channelId = "channel1";
        NotificationChannel channel = new NotificationChannel(channelId, "hello",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(getApplicationContext(),channelId);
        mBuilder.setSmallIcon(R.drawable.onlineshopping);
        mBuilder.setContentTitle("Order Successful");
        mBuilder.setContentText("Your Order is successfully placed");
        mBuilder.setAutoCancel(true);
        Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0 , i1,
                PendingIntent.FLAG_IMMUTABLE);
        mBuilder.setContentIntent(pi);
        nm.notify(121 ,mBuilder.build());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}