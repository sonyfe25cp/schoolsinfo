package com.stech.action.amuse;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.stech.R;
import com.stech.custom.AmusementCustomLayout;
import com.stech.utils.Logger;

public class AmusementShow extends TabActivity{

	private String LAGTOG="AmusementShow";
	
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.i(LAGTOG, "on create~~~~~~");
		tabHost = getTabHost();
		AmusementCustomLayout rct = new AmusementCustomLayout(this);
		
        tabHost.addTab(tabHost.newTabSpec("ktv")
        		.setIndicator("KTV",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));
        tabHost.addTab(tabHost.newTabSpec("theater")
        		.setIndicator("电影院",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));
        tabHost.addTab(tabHost.newTabSpec("mall")
        		.setIndicator("商场",getResources().getDrawable(R.drawable.icon))
        		.setContent(rct));
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffFE7E01);
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffDBDBDB);
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffDBDBDB);
        
        tabHost.setCurrentTab(0);
        
        /*
         * 变换标签颜色
         */
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tableId) {
				Logger.i(LAGTOG, tableId);
				if(tableId.equals("ktv")){
					tabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffFE7E01);
			        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffDBDBDB);
			        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffDBDBDB);
				}else if(tableId.equals("theater")){
					tabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffFE7E01);
			        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffDBDBDB);
			        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffDBDBDB);
				}else{
					tabHost.getTabWidget().getChildAt(2).setBackgroundColor(0xffFE7E01);
			        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(0xffDBDBDB);
			        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(0xffDBDBDB);
				}
			}
        	
        });
        
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

}
