package com.stech.custom;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.stech.R;
import com.stech.data.TrafficData;
import com.stech.model.Traffic;
import com.stech.utils.Information;

/**
 * @author ChenJie
 * 
 *         同步数据用
 */
public class AsyncTrafficLoader {

	private Context activity;
	private TrafficAdapter nullAdapter;

	public AsyncTrafficLoader(Activity activity) {
		this.activity = activity;
		nullAdapter = new TrafficAdapter(activity, R.layout.traffic_item,
				new ArrayList<Traffic>());
	}

	public TrafficAdapter loadTrafficListAdapter(final int type,
			final TrafficListCallBack trafficListCallBack) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				trafficListCallBack.listLoaded((TrafficAdapter) message.obj);
			}
		};
		new Thread() {
			@Override
			public void run() {
				TrafficAdapter tAdapter = generateAdapter(type);
				Message message = handler.obtainMessage(0, tAdapter);
				handler.sendMessage(message);
			}
		}.start();
		return nullAdapter;

	};

	public TrafficAdapter generateAdapter(int type) {
		long campusId = Information.getCurrentCampusId(activity);// 获取系统存的campusId
		List<Traffic> restList = TrafficData.getByCampusAndType(activity,campusId, type);
		TrafficAdapter adapter = new TrafficAdapter(activity,
				R.layout.traffic_item, restList);
		return adapter;
	}

	public interface TrafficListCallBack {
		public void listLoaded(TrafficAdapter adapter);
	}

}
