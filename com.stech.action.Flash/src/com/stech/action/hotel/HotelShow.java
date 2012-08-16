package com.stech.action.hotel;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.stech.R;
import com.stech.custom.AsyncHotelLoader;
import com.stech.custom.AsyncHotelLoader.HotelListCallBack;
import com.stech.custom.HotelAdapter;
import com.stech.model.Hotel;
import com.stech.utils.Tools;

public class HotelShow extends Activity{
	private ListView listView;
	private LinearLayout linearLayout;
	private Dialog downloading;
	private LayoutInflater inflaterHelper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.hotel);
		listView=(ListView) this.findViewById(R.id.hotel_list);
		flashHotelList();
	}

	public void flashHotelList() {
		AsyncHotelLoader asyncHotelLoader = new AsyncHotelLoader(this);
		ListAdapter adapter = asyncHotelLoader.loadHotelListAdapter(
				new HotelListCallBack() {
					@Override
					public void listLoaded(HotelAdapter adapter) {
						listView.setAdapter(adapter);
						downloading.cancel();
					}
				});
		if (adapter.getCount() == 0) {
			downloading = Tools.downloadingDialog(this);
			downloading.show();
		}
		
		/*
		 * 点中条目时候弹框
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView = (ListView) arg0;
				Hotel click_item = (Hotel) listView
						.getItemAtPosition(arg2);
//				Dialog details_dialog = boomDetailsDialog(this, click_item);
//				details_dialog.show();
			}
		});
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
}
