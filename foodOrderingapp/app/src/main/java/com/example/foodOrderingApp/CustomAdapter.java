package com.example.foodOrderingApp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Button plus,minus,order,view3d;

    TextView quantity;
    int count=0;
    private Context context;
    private Activity activity;
    private ArrayList food_id, item, price;

    CustomAdapter(Activity activity, Context context, ArrayList food_id, ArrayList item, ArrayList price){
        this.activity = activity;
        this.context = context;
        this.food_id = food_id;
        this.item = item;
        this.price = price;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView")final int position) {
        count=0;
        holder.idtxt.setText(String.valueOf(food_id.get(position)));
        holder.itemtxt.setText(String.valueOf(item.get(position)));
        holder.pricetxt.setText(String.valueOf(price.get(position)));

        //Recyclerview onClickListener
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("id", String.valueOf(food_id.get(position)));
                intent.putExtra("item", String.valueOf(item.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));

                activity.startActivityForResult(intent, 2);
            }
        });
       holder.view3d.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, ARactivity.class);
               intent.putExtra("name", String.valueOf(item.get(position)));
               activity.startActivity(intent);
           }
       });



    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        Button order,view3d;

        TextView idtxt,itemtxt,pricetxt,quantity;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idtxt = itemView.findViewById(R.id.idtxt);
            itemtxt= itemView.findViewById(R.id.itemtxt);
            pricetxt = itemView.findViewById(R.id.pricetxt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            view3d=itemView.findViewById(R.id.View3d);
            order=itemView.findViewById(R.id.order2);



            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
