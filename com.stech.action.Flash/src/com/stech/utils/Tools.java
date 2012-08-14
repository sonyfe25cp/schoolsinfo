package com.stech.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.stech.R;

public class Tools {

	private static String LOGTAG="tools";
	
	public static String phoneNum(List<String> phoneList){
		StringBuilder sb=new StringBuilder();
		for(String phone:phoneList){
			sb.append(phone+",");
		}
		if(sb.length()>0){
			sb=sb.deleteCharAt(sb.length()-1);
		}
		String phoneNum=sb.toString().trim();
		return phoneNum;
	}
	
	public static List<String> phoneList(String phoneNum){
		String[] phone=phoneNum.split(",");
		ArrayList<String> phoneList=new ArrayList<String>();
		for(String tmp:phone){
			phoneList.add(tmp);
		}
		return phoneList;
	}
	
	
	public static void showToast(Context context,String msg){
	        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static Dialog exitDialog(final Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(R.string.exitMessage)
		       .setCancelable(false)
		       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		        	   Intent MyIntent = new Intent(Intent.ACTION_MAIN);
		        	   MyIntent.addCategory(Intent.CATEGORY_HOME);
		        	   context.startActivity(MyIntent);
		           }
		       })
		       .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		return builder.create();
	}
	
	public static boolean isConnectInternet(Context context) {
        ConnectivityManager conManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        boolean system_flag=false;
        if(networkInfo != null){  // 注意，这个判断一定要的哦，要不然会出错
        	system_flag=networkInfo.isAvailable();
        }
        boolean mannual_flag=Information.getAutoUpdate(context);
        return system_flag&&mannual_flag;
	}
}
