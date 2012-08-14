package com.stech.step;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stech.R;
import com.stech.action.ServiceMenu;
import com.stech.utils.Information;
import com.stech.utils.Tools;

/**
 * 第一步
 * @author Administratr
 *
 */
public class BITMainAction extends Activity{

	
	private Button zol_btn;
	private Button lit_btn;
	
	private final static long zol_id=1313291738l;
	private final static long lit_id=1313291745l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bit_main);
		
		zol_btn=(Button)findViewById(R.id.bit_zol);
		zol_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Information.setCurrentCampusId(BITMainAction.this, zol_id);
				Intent intent=new Intent(BITMainAction.this,ServiceMenu.class);
				startActivity(intent);
			}
		});
		
		lit_btn=(Button)findViewById(R.id.bit_lit);
		lit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Information.setCurrentCampusId(BITMainAction.this, lit_id);
				Intent intent=new Intent(BITMainAction.this,ServiceMenu.class);
				startActivity(intent);
			}
		});
		
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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
}
