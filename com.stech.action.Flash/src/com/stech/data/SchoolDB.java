package com.stech.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stech.db.DBOpenHelper;
import com.stech.model.Campus;
import com.stech.model.School;

public class SchoolDB {
	
	private String LOGTAG="SchoolDB";
	
	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase db;
	
	public SchoolDB(Context context){
		dbOpenHelper=new DBOpenHelper(context);
	}
	
	public boolean insert(School school){
		int id=school.getId();
		School old=select(id);
		if(old==null){
			insertSchool(school);
		}else{
			
		}
		return true;
	}
	
	private boolean insertSchool(School school){
		db=dbOpenHelper.getWritableDatabase();
		String sql="insert into "+DBOpenHelper.SCHOOL_TABLE_NAME 
			+"(_id,name) values(?,?)";
		db.execSQL(sql, new Object[]{school.getId(),school.getName()});
		
		ArrayList<Campus> campusList=school.getCampusList();
		for(Campus campus:campusList){
			String sql_campus = "insert into "+DBOpenHelper.CAMPUS_TABLE_NAME+" (_id,name,schoolid) values (?,?,?)";
			db.execSQL(sql_campus, new Object[]{campus.getId(),campus.getCampus_name(),school.getId()});
		}
		
		db.close();
		return true;
	}

	public School select(int id){
		db=dbOpenHelper.getWritableDatabase();
		String sql="select * from "+DBOpenHelper.SCHOOL_TABLE_NAME +" where _id = ?";
		Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(id)});
		School school=null;
		if(cursor.moveToNext()){
			school=new School();
			school.setId(cursor.getInt(0));
			school.setName(cursor.getString(1));
		}
		return school;
	}
	
	ArrayList<Campus> getCampusListFromCursor(Cursor cursor){
		ArrayList<Campus> campusList=new ArrayList<Campus>();
		Campus campus=null;
		while(cursor.moveToNext()){
			campus=new Campus();
			campus.setId(cursor.getInt(0));
			campus.setCampus_name(cursor.getString(1));
			
//			Logger.i(LOGTAG, campus.toString()+" --- ");
			
			campusList.add(campus);
		}
		cursor.close();
		return campusList;
	}
	
	public List<School> selectList(){
		db=dbOpenHelper.getWritableDatabase();
		String sql="select * from "+DBOpenHelper.SCHOOL_TABLE_NAME;
		Cursor cursor=db.rawQuery(sql, null);
		List<School> schoolList=new ArrayList<School>();
		School school=null;
		while(cursor.moveToNext()){
			school=new School();
			school.setId(cursor.getInt(0));
			school.setName(cursor.getString(1));
			
			String campus_sql="select * from "+DBOpenHelper.CAMPUS_TABLE_NAME +" where schoolid = "+school.getId();
			Cursor campus_cursor=db.rawQuery(campus_sql, null);
			ArrayList<Campus> campusList=getCampusListFromCursor(campus_cursor);
			
//			Logger.i(LOGTAG, "id:"+school.getId());
//			Logger.i(LOGTAG, "name:"+school.getName());
			
			school.setCampusList(campusList);
			schoolList.add(school);
		}
		db.close();
		return schoolList;
	}

}
