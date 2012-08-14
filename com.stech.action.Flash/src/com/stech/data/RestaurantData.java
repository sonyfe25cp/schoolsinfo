package com.stech.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.stech.model.Restaurant;
import com.stech.parse.XmlParseForRestaurant;
import com.stech.utils.Information;
import com.stech.utils.Logger;
import com.stech.utils.NetTools;
import com.stech.utils.Tools;

public class RestaurantData {

	private static String LOGTAG="RestaurantData";
	
	public static List<Restaurant> getByCampusAndType(Context context,long campusId,int type){
		
		RestaurantDB rdb=new RestaurantDB(context);
		List<Restaurant> restList=null;
		if(Tools.isConnectInternet(context)){//本地没有
			Logger.i(LOGTAG, "local restuarant is null ");
			restList=getRestListFromNet(context,campusId, type);
		}else{
			restList=rdb.selectList(campusId, type);
			if(restList.size()==0){
				Information.setAutoUpdate(context, true);
				restList=getRestListFromNet(context,campusId, type);
				Information.setAutoUpdate(context, false);
				if(restList.size()==0){
					Tools.showToast(context, "暂无数据,请检查网络是否可用~");
				}
			}
		}
		
		return restList;
	}
	
	public static List<Restaurant> getRestListFromNet(Context context,long campusId,int type){
		List<Restaurant> restList=new ArrayList<Restaurant>();
		String url=Information.RESTAURANTINFOURL;
		Logger.i(LOGTAG, "get restuarant from net ");
		InputStream is=null;
		try {
			url=url+"?campusid="+campusId+"&resttype="+type;
			Logger.i(LOGTAG, url);
			is=NetTools.getContent(url);
			XmlParseForRestaurant xpfr=new XmlParseForRestaurant(is);
			xpfr.parse();
			restList=xpfr.getRestList();
		} catch (IOException e) {
			Logger.e(LOGTAG, "parse rest from net is error");
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Logger.i(LOGTAG, "restlist from net is "+restList.size());
		if(restList!=null&&restList.size()>0){
			RestaurantDB rdb=new RestaurantDB(context);
			for(Restaurant r:restList){
				Logger.i(LOGTAG, "insert all data into db");
				rdb.insertRestaurant(r);
			}
		}
		return restList;
	}
	
	
	
}
