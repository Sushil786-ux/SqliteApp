package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="NewDb.db";
    public static final String TABLE_NAME="tbl";
    public static final String Col_1="ID";
    public static final String Col_2="NAME";
    public static final String Col_3="EMAIL";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String phone,String place){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,name);
        contentValues.put(Col_2,phone);
        contentValues.put(Col_3,place);
        Long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }
    public boolean updateData(String id, String item, Integer qty){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,item);
        contentValues.put(Col_3,qty);

        Integer res;
        res = db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if (res > 0)
            return true;
        else
            return false;
    }

    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }

}
