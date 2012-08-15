package com.stech.action;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.stech.R;
import com.stech.custom.SchoolAdapter;
import com.stech.data.SchoolData;
import com.stech.model.School;
import com.stech.utils.Information;
import com.stech.utils.Logger;

/**
 * @author ChenJie
 * 
 * 学校选择界面
 */
public class MainAction extends Activity{
	
	private String LOGTAG="MainAction";
	
	private long DEFAULTCAMPUSID=0;

	private List<School> schoolsList;
	private ExpandableListView schoolsListView;
	private SchoolAdapter adapter;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Logger.i(LOGTAG, "create~");
		setContentView(R.layout.main);
		showExpandAbleList();
	}
	
	public void showExpandAbleList(){
		DEFAULTCAMPUSID=Information.getCurrentCampusId(this);
		Logger.i(LOGTAG, "DEFAULTCAMPUSID is -- "+DEFAULTCAMPUSID);
		if(DEFAULTCAMPUSID==0l){
			Logger.i(LOGTAG, "DEFAULTCAMPUSID is 0");
			schoolsList=SchoolData.schoolList(this);
			schoolsListView=(ExpandableListView) findViewById(R.id.campus_list);
			adapter=new SchoolAdapter(this,schoolsList);
			schoolsListView.setAdapter(adapter);
			Logger.i(LOGTAG, "adapter.size:"+adapter.getGroupCount());
			schoolsListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					Logger.i(LOGTAG, "id:"+id);
					Information.setCurrentCampusId(MainAction.this, id);
					intent = new Intent(MainAction.this,ServiceMenu.class);
					startActivity(intent);
					return true;
				}
			});
		}else{
			Logger.i(LOGTAG, "DEFAULTCAMPUSID is available ");
			intent = new Intent(this,ServiceMenu.class);
			startActivity(intent);
		}
	}
	
	@Override
	protected void onPause() {
		Logger.i(LOGTAG, "pause~");
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Logger.i(LOGTAG, "resume~");
		showExpandAbleList();
	}
	
	
}
