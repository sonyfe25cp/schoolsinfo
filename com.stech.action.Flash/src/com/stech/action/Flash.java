package com.stech.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;

import com.stech.R;
import com.stech.step.BITMainAction;
import com.stech.utils.Logger;

public class Flash extends Activity {
    /** Called when the activity is first created. */
    
	private String LogTag="SplashActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.i(LogTag, "create le~");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.flash);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Logger.i(LogTag, "resume le~");
		new Thread(){
			public void run(){
			SystemClock.sleep(3000);
			Intent intent = new Intent(Flash.this, BITMainAction.class);
			startActivity(intent);
			}
		}.start();
	}
}