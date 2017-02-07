package com.sandyz.databaseassignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by santosh on 04-02-2017.
 */

public class Dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE_NAME = "Employee";
    public static final int DATABASE_VERSION =1;
    public static final String ID = "ID";
    public static final String COLOUMN_ONE = " emp_name ";
    public static final String COLOUMN_TWO = " emp_age ";
    public static final String COLOUMN_THREE = " pic ";
    byte[] pic;
    public  SQLiteDatabase database = null;


    //empty constructor


    public Dbhelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    // create the table


    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLOUMN_ONE + "TEXT," +
                COLOUMN_TWO + "TEXT," +
                COLOUMN_THREE + "BLOB)";

        db.execSQL(table);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DELETE = "Drop table if exists" + TABLE_NAME;
        db.execSQL(DELETE);
    }

    public void openDB() throws SQLException
    {
        Log.i("open db databse","Checking.....");
        if (this.database==null){

            Log.i("openDB","Creating db.....");
            this.database=this.getWritableDatabase();

        }
    }

    // close db if open

    public void closeDB()
    {
        Log.i("closedb","checking db.....");
        if (this.database.isOpen()){
            Log.i("closedb","closing db.....");
            this.database.close();
        }
    }

    public long  insertData(beans beans){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLOUMN_ONE, beans.getName());
        contentValues.put(COLOUMN_TWO,beans.getAge());
        contentValues.put(COLOUMN_THREE,getBytes(beans.getBitmap()));

        Log.i("insertData","inserting data...");
        return this.database.insert(TABLE_NAME,null,contentValues);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] pic) {
        return BitmapFactory.decodeByteArray(pic, 0, pic.length);
    }


public beans getAllEmployee()throws SQLException{

    Cursor cursor = database.query(TABLE_NAME,new String[]{COLOUMN_ONE,COLOUMN_TWO,COLOUMN_THREE},null,null,null,null,null);


    if (cursor.moveToNext()){


        String name = cursor.getString(0);//unreferenced variable -1 position
        String age = cursor.getString(1);
        byte[] blob = cursor.getBlob(2);
        cursor.close();
        return new beans(getImage(blob), name, age);

    }
    cursor.close();
    return null;



}

}
