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

public class FailedPayment extends AppCompatActivity {
Button tryagain;
    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_payment);
        tryagain=findViewById(R.id.tryagain);
        Intent intent=getIntent();
        String n= intent.getStringExtra("name");
        String channelId = "channel1";
        NotificationChannel channel = new NotificationChannel(channelId, "hello",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(getApplicationContext(),channelId);
        mBuilder.setSmallIcon(R.drawable.onlineshopping);
        mBuilder.setContentTitle("Order Failed");
        mBuilder.setContentText("Please try again.");
        mBuilder.setAutoCancel(true);
        Intent i1 = new Intent(getApplicationContext(),BillGeneration.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0 , i1,
                PendingIntent.FLAG_IMMUTABLE);
        mBuilder.setContentIntent(pi);
        nm.notify(121 ,mBuilder.build());
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),BillGeneration.class);
                i.putExtra("name",n);
                startActivity(i);
                int j;
            }
        });
    }
}