package com.stech.custom;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stech.R;
import com.stech.model.Campus;
import com.stech.model.School;

public class SchoolAdapter extends BaseExpandableListAdapter {

	private String LOGTAG = "schooladapter";
	private LayoutInflater mInflater;
	private Activity activity;
	public List<List<Campus>> child;
	public List<School> group;

	public Bitmap mIcon;

	public SchoolAdapter(Activity activity, List<School> schoolList) {
		this.activity = activity;
		group = new ArrayList<School>();
		child = new ArrayList<List<Campus>>();
		mInflater = LayoutInflater.from(activity);
		for (School tmp : schoolList) {
			// Logger.i(LOGTAG, ""+(tmp==null?true:false));
			group.add(tmp);
			// Logger.i(LOGTAG,
			// "tmp.getCampusList:"+tmp.getCampusList()==null?true+"":tmp.getCampusList().size()+"");
			child.add(tmp.getCampusList());
		}
		// Logger.i(LOGTAG, "child.size: "+child.size());
		// Logger.i(LOGTAG, "group.size: "+group.size());
		mIcon = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.icon);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

		return child.get(groupPosition).get(childPosition);

	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return child.get(groupPosition).get(childPosition).getId();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		return getView(groupPosition, childPosition, convertView, parent);
	}

	public View getView(int groupPosition, int childPosition, View convertView,
			ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.schoolitem, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.campus_name);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.campus_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String name = child.get(groupPosition).get(childPosition)
				.getCampus_name();
		holder.textView.setText(name);
		holder.icon.setImageBitmap(mIcon);
		return convertView;
	}

	public TextView getChildView(Campus campus) {
		TextView textView = new TextView(activity);
		textView.setTextSize(20);
		textView.setText(campus.getCampus_name());
		textView.setPadding(36, 0, 0, 0);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// Logger.i(LOGTAG, "child:"+child.size());
		// Logger.i(LOGTAG, "groupPosition:"+groupPosition);
		// Logger.i(LOGTAG, "size:"+child.get(groupPosition));
		return child.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return group.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return group.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return group.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getGroupView(group.get(groupPosition).getName());
	}

	public TextView getGroupView(String schoolName) {
		TextView textView = new TextView(activity);
		textView.setTextSize(20);
		textView.setText(schoolName);
		textView.setPadding(36, 0, 0, 0);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	static class ViewHolder {
		TextView textView;
		ImageView icon;
	}
}
