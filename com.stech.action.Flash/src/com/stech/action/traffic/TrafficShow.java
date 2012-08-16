package com.stech.action.traffic;

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
import com.stech.custom.AsyncTrafficLoader;
import com.stech.custom.AsyncTrafficLoader.TrafficListCallBack;
import com.stech.custom.TrafficAdapter;
import com.stech.model.Traffic;
import com.stech.utils.Tools;

public class TrafficShow extends Activity {
	private ListView listView;
	private LinearLayout linearLayout;
	private Dialog downloading;
	private LayoutInflater inflaterHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.traffic);
		listView = (ListView) this.findViewById(R.id.traffic_list);
		flashTrafficList();

	}

	public void flashTrafficList() {
		AsyncTrafficLoader asyncTrafficLoader = new AsyncTrafficLoader(this);
		ListAdapter adapter = asyncTrafficLoader.loadTrafficListAdapter(0,
				new TrafficListCallBack() {
					@Override
					public void listLoaded(TrafficAdapter adapter) {
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
				Traffic click_item = (Traffic) listView.getItemAtPosition(arg2);
				// Dialog details_dialog = boomDetailsDialog(this, click_item);
				// details_dialog.show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
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
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
