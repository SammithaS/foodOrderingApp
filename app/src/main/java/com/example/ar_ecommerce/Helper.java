package com.example.ar_ecommerce;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class Helper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "FOOD.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Food_record";
    private static final String COLUMN_ID = "food_id";
    private static final String COLUMN_TITLE = "Item";

    private static final String COLUMN_AMOUNT = "Price";
    private static final String TABLE_NAME1 = "Customer_record";
    private static final String COLUMN_ID1 = "orderId";
    private static final String COLUMN_TITLE1= "customerName";

    private static final String COLUMN_AMOUNT1 = "Amount";
    private static final String TABLE_NAME2 = "Customer_food";
    private static final String COLUMN_ID2 = "orderId";
    private static final String COLUMN_ID22= "food_Id";

    private static final String COLUMN_QUANTITY2= "Quantity";

    Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AMOUNT + " INTEGER);";
        db.execSQL(query);

        String q = "CREATE TABLE " + TABLE_NAME1 +
                " (" + COLUMN_ID1 + " TEXT, " +
                COLUMN_TITLE1 + " TEXT, " +
                COLUMN_AMOUNT1 + " INTEGER);";
        db.execSQL(q);
        String r = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " TEXT, " +
                COLUMN_ID22 + " INTEGER, " +
                COLUMN_QUANTITY2 + " INTEGER);";
        db.execSQL(r);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void addFood(String title, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AMOUNT, price);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("Range")
    void CustomerFoodrecords(int id, String Name, int quantity){



       String qu="Select orderId from Customer_record where customerName='"+Name+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(qu,null);
        String OrderId="";
        ContentValues cn1 = new ContentValues();
        if(cursor.moveToFirst()){
            do{
                OrderId=cursor.getString(cursor.getColumnIndex("orderId"));
            }while(cursor.moveToNext());
        }
        if(OrderId!="") {
            cn1.put(COLUMN_ID2, OrderId);
            cn1.put(COLUMN_ID22, id);
            cn1.put(COLUMN_QUANTITY2, quantity);
            long result = db.insert(TABLE_NAME2, null, cn1);
            if (result == -1) {
                Toast.makeText(context, OrderId, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context, "OrderId not present", Toast.LENGTH_SHORT).show();}
    }
    void addcustomer(String id,String title, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(COLUMN_ID1,id);
        cn.put(COLUMN_TITLE1, title);
        cn.put(COLUMN_AMOUNT1, price);
        long result = db.insert(TABLE_NAME1,null, cn);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllData1(){
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String title, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AMOUNT, price);

        long result = db.update(TABLE_NAME, cv, "item=?", new String[]{title});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("Range")
    void updateAmount(String Name){
        String qu = "SELECT " + COLUMN_ID1 + " FROM " + TABLE_NAME1 + " WHERE " + COLUMN_TITLE1 + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(qu, new String[]{Name});
        String orderId = "";

        if (cursor.moveToFirst()) {
            orderId = cursor.getString(cursor.getColumnIndex(COLUMN_ID1));
        }

        int totalAmount = 0;
        cursor.close();

        if (!orderId.isEmpty()) {
            String ue = "SELECT " + COLUMN_ID22 + ", " + COLUMN_QUANTITY2 + " FROM " + TABLE_NAME2 + " WHERE " + COLUMN_ID2 + " = ?";
            Cursor cursor1 = db.rawQuery(ue, new String[]{orderId});

            if (cursor1.moveToFirst()) {
                do {
                    int foodId = cursor1.getInt(cursor1.getColumnIndex(COLUMN_ID22));
                    int quantity = cursor1.getInt(cursor1.getColumnIndex(COLUMN_QUANTITY2));

                    String pc = "SELECT " + COLUMN_AMOUNT + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
                    Cursor priceCursor = db.rawQuery(pc, new String[]{String.valueOf(foodId)});

                    if (priceCursor.moveToFirst()) {
                        int price = priceCursor.getInt(priceCursor.getColumnIndex(COLUMN_AMOUNT));

                        int subtotal = price * quantity;

                        totalAmount += subtotal;
                    }
                    priceCursor.close();
                } while (cursor1.moveToNext());
            }
            cursor1.close();

            ContentValues cn2 = new ContentValues();
            cn2.put(COLUMN_AMOUNT1, totalAmount);

            int result = db.update(TABLE_NAME1, cn2, COLUMN_ID1 + " = ?", new String[]{orderId});
            if (result > 0) {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "OrderId not present", Toast.LENGTH_SHORT).show();
        }

        db.close();
}

  @SuppressLint("Range")
    int getTotalAmountByCustomerName(String customerName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_AMOUNT1 + " FROM " + TABLE_NAME1 +
                " WHERE " + COLUMN_TITLE1 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{customerName});

        int totalAmount = 0;

        if (cursor.moveToFirst()) {
            totalAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT1));
        }

        cursor.close();
        db.close();

        return totalAmount;
    }

}
