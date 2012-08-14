package com.stech.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stech.utils.Logger;

public class DBOpenHelper extends SQLiteOpenHelper{

	private String LogTag="db";
	
	SQLiteDatabase db;  
    Context context;
    
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME="omar_tech";
    public final static String SCHOOL_TABLE_NAME="schooltable";
    public final static String CAMPUS_TABLE_NAME="campustable";
    public final static String RESTAURANT_TABLE_NAME="restaurant";
    public final static String AMUSEMENT_TABLE_NAME="amusementtable";
    public final static String HOTEL_TABLE_NAME="hoteltable";
    public final static String TRAFFIC_TABLE_NAME="traffictable";
    
    private final static String KEY_ID="_id";
    
    private static final String SCHOOL_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + SCHOOL_TABLE_NAME + "(" +
                KEY_ID + " INTEGER ," +
                "name TEXT);";
    
    private static final String CAMPUS_TABLE_CREATE =
        "CREATE TABLE IF NOT EXISTS " + CAMPUS_TABLE_NAME + "(" +
        KEY_ID + " INTEGER primary key ," +
        "name TEXT,"+
        "schoolid Integer);";
    
    private static final String RESTAURANT_TABLE_CREATE=
    	"CREATE TABLE IF NOT EXISTS " + RESTAURANT_TABLE_NAME + "(" +
        KEY_ID + " INTEGER ," +
        "name TEXT," +
        "location TEXT," +
        "desc TEXT," +
        "issale INTEGER," +
        "isrest INTEGER," +
        "isshitang INTEGER," +
        "phone TEXT," +
        "score FLOAT," +
        "imgurl TEXT," +
        "menuurl TEXT," +
        "defaultrank TEXT," +
        "campusid INTEGER" +
        ");";

    

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.i(LogTag, "create database");
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
    	Logger.i(LogTag, "create tables");

    	db.execSQL(SCHOOL_TABLE_CREATE);
        db.execSQL(CAMPUS_TABLE_CREATE);
        db.execSQL(RESTAURANT_TABLE_CREATE);
        
        Logger.i(LogTag, "on create db over");
    }
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


}
