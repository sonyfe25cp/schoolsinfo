package com.stech.utils;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author ChenJie
 *
 * 基本网络链接
 */
public class Information {
	
//	public static final String SCHOOLINFOURL="http://schoolsinfo.omartech.com/schools.xml";
	public static final String SCHOOLINFOURL="http://schoolsinfo.sinaapp.com/school/schoolsListForAndroid.php";
	
	static String host="http://schoolsinfo.sinaapp.com";
//	static String host="http://www.schoolsinfo.com/schoolsinfo";
	public static final String RESTAURANTINFOURL=host+"/restaurant/listForAndroid.php";
	
	public static final String HOTELINFOURL=host+"/hotel/listForAndroid.php";
	
	public static final String AMUSEMENTINFOURL=host+"/amusement/listForAndroid.php";
	
	public static final String TRAFFICINFOURL=host+"/traffic/listForAndroid.php";
	
	private static final String SCHOOLSINFO="schoolsinfo";
	private static final String CURRENTCAMPUSID="currentcampusid";
	private static final String RANDOMID="randomid";
	private static final String AUTOUPDATE="autoupdate";
	
	public static boolean setCurrentCampusId(Context context,long campusId){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		Editor e=sp.edit();
		e.putLong(CURRENTCAMPUSID, campusId);
		boolean flag=e.commit();
		return flag;
	}
	
	public static boolean setRandomId(Context context){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		Editor e=sp.edit();
		int randomid= new Random().nextInt();
		e.putInt(RANDOMID, randomid);
		boolean flag=e.commit();
		return flag;
	}
	
	public static long getCurrentCampusId(Context context){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		return sp.getLong(CURRENTCAMPUSID, 0l);
	}
	
	public static int getRandomId(Context context){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		return sp.getInt(RANDOMID, 0);
	}

	public static boolean setAutoUpdate(Context context,boolean update_flag){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		Editor e=sp.edit();
		e.putInt(AUTOUPDATE, update_flag?0:1);
		boolean flag=e.commit();
		return flag;
	}
	
	public static boolean getAutoUpdate(Context context){
		SharedPreferences sp=context.getSharedPreferences(SCHOOLSINFO, Context.MODE_PRIVATE);
		int auto= sp.getInt(AUTOUPDATE, 0);
		if(auto == 0){
			return true;
		}else{
			return false;
		}
	}
}
