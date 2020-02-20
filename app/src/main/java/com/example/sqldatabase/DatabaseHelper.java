package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName="Student.db";
    private static final String TableName="StudentDetails";
    private static final String Id="Id";
    private static final String Name="Name";
    private static final String Age="Age";
    private static final String Gender="Gender";
    private static final String CREATE_TABLE="CREATE TABLE "+TableName+"("+ Id +" INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " VARCHAR(200)," + Age + " INTEGER(3)," + Gender + " VARCHAR(10))";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TableName;
    private static final String SHOW_DATA="SELECT * FROM "+TableName;

    public static final int Version=10;

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{

            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Database Created",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{

            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context,"Database Updated",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    public long insertData(String name,String age,String gender)
    {
       SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Age,age);
        contentValues.put(Gender,gender);
        long rowId=sqLiteDatabase.insert(TableName,null,contentValues);
        return rowId;
    }
    public Cursor ShowData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SHOW_DATA,null);
        return cursor;
    }
}
