package com.stech.custom;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.stech.R;
import com.stech.data.RestaurantData;
import com.stech.model.Restaurant;
import com.stech.utils.Information;

/**
 * @author ChenJie
 *
 * 同步数据用
 */
public class AsyncRestaurantLoader {
	
	private Context activity;
	private RestaurantAdapter nullAdapter;
	
	public AsyncRestaurantLoader(Activity activity) {
		this.activity=activity;
		nullAdapter=new RestaurantAdapter(activity,R.layout.restaurant_item,new ArrayList<Restaurant>());
	}
	
	
	public  RestaurantAdapter loadRestaurantListAdapter(final int type,final RestaurantListCallBack restaurantListCallBack) {
		final Handler handler = new Handler() {
    		@Override
    		public void handleMessage(Message message) {
    			restaurantListCallBack.tuanListLoaded((RestaurantAdapter)message.obj);
    		}
    	};
    	new Thread() {
    		@Override
    		public void run() {
    			RestaurantAdapter tAdapter=generateAdapter(type);
                Message message = handler.obtainMessage(0, tAdapter);
                handler.sendMessage(message);
    		}
    	}.start();
    	return nullAdapter;
		
	};
	
	public RestaurantAdapter generateAdapter(int type){
		long campusId=Information.getCurrentCampusId(activity);//获取系统存的campusId
		List<Restaurant> restList=RestaurantData.getByCampusAndType(activity,campusId,type);
		RestaurantAdapter adapter=new RestaurantAdapter(activity, R.layout.restaurant_item, restList);
		return adapter;
	}

	public interface RestaurantListCallBack {
        public void tuanListLoaded(RestaurantAdapter adapter);
    }
	
	
}
