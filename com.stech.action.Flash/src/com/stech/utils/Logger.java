package com.stech.utils;

import android.util.Log;

public class Logger {
	
	private static boolean isInfoOn=true;
	private static boolean isErrorOn=true;
	
	
	public static void i(String LogTag,String message){
		if(isInfoOn)
			Log.i(LogTag, message);
	}
	
	public static void e(String LogTag,String message){
		if(isErrorOn)
			Log.e(LogTag, message);
	}

}
