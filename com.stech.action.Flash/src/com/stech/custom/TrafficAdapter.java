package com.stech.custom;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stech.R;
import com.stech.model.Traffic;

public class TrafficAdapter extends ArrayAdapter {

	private String LOGTAG = "TrafficAdapter";
	private int resource;
	private Traffic traffic;
	private TextView name_textview;
	private TextView location_textview;

	@SuppressWarnings("unchecked")
	public TrafficAdapter(Context context, int textViewResourceId, List objects) {
		super(context, textViewResourceId, objects);
		this.resource = textViewResourceId;
	}

	public Object getItemAtPosition(int position) {
		traffic = (Traffic) getItem(position);
		return traffic;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout trafficLayout = null;

		if (convertView == null) {
			trafficLayout = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					inflater);
			// Logger.i(LOGTAG, "resource id :"+resource);
			vi.inflate(resource, trafficLayout, true);
		} else {
			trafficLayout = (LinearLayout) convertView;
		}

		traffic = (Traffic) getItem(position);
		if (traffic != null) {
			name_textview = (TextView) trafficLayout
					.findViewById(R.id.traffic_name);
			name_textview.setText(traffic.getName());

			location_textview = (TextView) trafficLayout
					.findViewById(R.id.traffic_location);
			
			location_textview.setText(Traffic.getLocationName(traffic.getLocationType()));

		}
		return trafficLayout;

	}
}
