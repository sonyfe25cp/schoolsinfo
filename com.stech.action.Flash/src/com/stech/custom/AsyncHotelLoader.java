package com.stech.custom;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.stech.R;
import com.stech.data.HotelData;
import com.stech.model.Hotel;
import com.stech.utils.Information;

/**
 * @author ChenJie
 *
 * 同步数据用
 */
public class AsyncHotelLoader {
	
	private Context activity;
	private HotelAdapter nullAdapter;
	
	public AsyncHotelLoader(Activity activity) {
		this.activity=activity;
		nullAdapter=new HotelAdapter(activity,R.layout.hotel_item,new ArrayList<Hotel>());
	}
	
	
	public  HotelAdapter loadHotelListAdapter(final HotelListCallBack hotelListCallBack) {
		final Handler handler = new Handler() {
    		@Override
    		public void handleMessage(Message message) {
    			hotelListCallBack.listLoaded((HotelAdapter)message.obj);
    		}
    	};
    	new Thread() {
    		@Override
    		public void run() {
    			HotelAdapter tAdapter=generateAdapter();
                Message message = handler.obtainMessage(0, tAdapter);
                handler.sendMessage(message);
    		}
    	}.start();
    	return nullAdapter;
		
	};
	
	public HotelAdapter generateAdapter(){
		long campusId=Information.getCurrentCampusId(activity);//获取系统存的campusId
		List<Hotel> hotelList=HotelData.getByCampusAndType(activity,campusId);
		HotelAdapter adapter=new HotelAdapter(activity, R.layout.hotel_item, hotelList);
		return adapter;
	}

	public interface HotelListCallBack {
        public void listLoaded(HotelAdapter adapter);
    }
	
	
}
