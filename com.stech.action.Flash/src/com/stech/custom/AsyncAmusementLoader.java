package com.stech.custom;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.stech.R;
import com.stech.data.AmusementData;
import com.stech.model.Amusement;
import com.stech.utils.Information;

/**
 * @author ChenJie
 * 
 *         同步数据用
 */
public class AsyncAmusementLoader {

	private Context activity;
	private AmusementAdapter nullAdapter;

	public AsyncAmusementLoader(Activity activity) {
		this.activity = activity;
		nullAdapter = new AmusementAdapter(activity, R.layout.amusement_item,
				new ArrayList<Amusement>());
	}

	public AmusementAdapter loadAmusementListAdapter(final int type,
			final AmusementListCallBack amusementListCallBack) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				amusementListCallBack
						.listLoaded((AmusementAdapter) message.obj);
			}
		};
		new Thread() {
			@Override
			public void run() {
				AmusementAdapter tAdapter = generateAdapter(type);
				Message message = handler.obtainMessage(0, tAdapter);
				handler.sendMessage(message);
			}
		}.start();
		return nullAdapter;

	};

	public AmusementAdapter generateAdapter(int type) {
		long campusId = Information.getCurrentCampusId(activity);// 获取系统存的campusId
		List<Amusement> restList = AmusementData.getByCampusAndType(activity,
				campusId, type);
		AmusementAdapter adapter = new AmusementAdapter(activity,
				R.layout.amusement_item, restList);
		return adapter;
	}

	public interface AmusementListCallBack {
		public void listLoaded(AmusementAdapter adapter);
	}

}
