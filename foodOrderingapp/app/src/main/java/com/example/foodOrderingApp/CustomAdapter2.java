package com.example.foodOrderingApp;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder1> {
    Button plus,minus,order;
    TextView quantity;
    int count=0;
    private Context context;
    private Activity activity;
    private ArrayList food_id, item, price;

    CustomAdapter2(Activity activity, Context context, ArrayList food_id, ArrayList item, ArrayList price){
        this.activity = activity;
        this.context = context;
        this.food_id = food_id;
        this.item = item;
        this.price = price;

    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row1, parent, false);
        return new MyViewHolder1(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder1 holder, @SuppressLint("RecyclerView")final int position) {
        count=0;
        holder.idtxt.setText(String.valueOf(food_id.get(position)));
        holder.itemtxt.setText(String.valueOf(item.get(position)));
        holder.quantity.setText(String.valueOf(price.get(position)));


    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {

        TextView idtxt,itemtxt,quantity;
        LinearLayout mainLayout;

        MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            idtxt = itemView.findViewById(R.id.itemid);
            itemtxt= itemView.findViewById(R.id.orderid);
            quantity = itemView.findViewById(R.id.quantities);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
