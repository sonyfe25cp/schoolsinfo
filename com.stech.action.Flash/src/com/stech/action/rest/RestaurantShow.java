package com.stech.action.rest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

import com.stech.R;
import com.stech.custom.RestaurantCustomLayout;
import com.stech.utils.Logger;

public class RestaurantShow extends TabActivity{

	private String LAGTOG="RestaurantShow";
	
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.i(LAGTOG, "on create~~~~~~");
		tabHost = getTabHost();
		RestaurantCustomLayout rct = new RestaurantCustomLayout(this);
		
        tabHost.addTab(tabHost.newTabSpec("outsale")
        		.setIndicator("外卖",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));
        tabHost.addTab(tabHost.newTabSpec("rest")
        		.setIndicator("餐馆",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));
        tabHost.addTab(tabHost.newTabSpec("shitang")
        		.setIndicator("食堂",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));

        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffFE7E01);
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffDBDBDB);
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffDBDBDB);
        
//        tabHost.setCurrentTab(0);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.rest_menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_new_rest:
			Intent intent = new Intent(this,AddNewRestAction.class);
			startActivity(intent);
			return true;
//		case R.id.menu_about:
//			Intent intent=new Intent(this,About.class);
//			startActivity(intent);
//			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
