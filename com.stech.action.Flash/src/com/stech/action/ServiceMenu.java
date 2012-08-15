package com.stech.action;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stech.R;
import com.stech.action.rest.RestaurantShow;
import com.stech.step.BITMainAction;
import com.stech.utils.Information;
import com.stech.utils.Tools;

/**
 * @author ChenJie
 *
 * 加载各种功能
 */
public class ServiceMenu extends Activity {

	private Intent intent=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_menu);
		
		Button service_menu_eat=(Button) findViewById(R.id.service_menu_eat);
		service_menu_eat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(ServiceMenu.this, RestaurantShow.class);
				startActivity(intent);
			}
		});
		
		
		Button service_menu_traffic=(Button) findViewById(R.id.service_menu_traffic);
		service_menu_traffic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.return_to_campus, menu);
	    boolean auto_update_flag=Information.getAutoUpdate(this);
	    if(auto_update_flag){
	    	menu.findItem(R.id.sys_config).setTitle(R.string.auto_update_not);
	    }else{
	    	menu.findItem(R.id.sys_config).setTitle(R.string.auto_update_yes);
	    }
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.change_campus:
			Information.setCurrentCampusId(ServiceMenu.this, 0l);
			Tools.showToast(this, "更换一个校区看看");
			intent = new Intent(ServiceMenu.this, BITMainAction.class);
			startActivity(intent);
			return true;
		case R.id.sys_config:
			boolean auto_update_flag=Information.getAutoUpdate(this);
			if(auto_update_flag){
		    	item.setTitle(R.string.auto_update_not);
		    	Information.setAutoUpdate(this, false);
		    	Tools.showToast(this, "已关闭自动更新");
		    }else{
		    	item.setTitle(R.string.auto_update_yes);
		    	Information.setAutoUpdate(this, true);
		    	Tools.showToast(this, "已开启自动更新");
		    }
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//按下键盘上返回按钮
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Dialog exit_dialog=Tools.exitDialog(this);
			exit_dialog.show();
			return true;
		}else{		
			return super.onKeyDown(keyCode, event);
		}
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	

}
