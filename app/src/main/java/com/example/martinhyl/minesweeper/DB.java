package com.example.martinhyl.minesweeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Martin Hyl on 12/3/2017.
 */

public class DB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LeaderBoard.db";
    public static final String CONTACTS_TABLE_NAME = "leaders";
    public static final String CONTACTS_COLUMN_LEVEL = "level";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_TIME = "time";

    //public static ArrayList<String> arrayList = new ArrayList<String>();

    public DB(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table contacts " + "(id integer primary key, name text)");
        db.execSQL("CREATE TABLE leaders " + "(level INTEGER PRIMARY KEY, name TEXT, time INTEGER)");
        db.execSQL("INSERT INTO leaders (level,name,time) VALUES (1,'noname',9999)");
        db.execSQL("INSERT INTO leaders (level,name,time) VALUES (2,'noname',9999)");
        db.execSQL("INSERT INTO leaders (level,name,time) VALUES (3,'noname',9999)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS leaders");
        onCreate(db);
    }



    //Cursor representuje vracena data
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from leaders where level=" + id + "", null);
        //Cursor res =  db.rawQuery( "select * from contacts LIMIT 1 OFFSET "+id+"", null );
        return res;
    }

    public boolean updateLeader (int level, String name, int time)
    {
        //TODO update zaznam
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("level", level);
        contentValues.put("time", time);
        db.update("leaders",contentValues,"level="+level,null);
        return true;
    }



}