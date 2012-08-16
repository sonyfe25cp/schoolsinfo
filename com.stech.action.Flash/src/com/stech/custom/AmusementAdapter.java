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
import com.stech.model.Amusement;
import com.stech.utils.Tools;

public class AmusementAdapter extends ArrayAdapter {

	private String LOGTAG = "AmusementAdapter";
	private int resource;
	private Amusement amusement;
	private TextView name_textview;
	private TextView location_textview;
	private TextView phoneList_textview;

	@SuppressWarnings("unchecked")
	public AmusementAdapter(Context context, int textViewResourceId,
			List objects) {
		super(context, textViewResourceId, objects);
		this.resource = textViewResourceId;
	}

	public Object getItemAtPosition(int position) {
		amusement = (Amusement) getItem(position);
		return amusement;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout amusementLayout = null;

		if (convertView == null) {
			amusementLayout = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					inflater);
			// Logger.i(LOGTAG, "resource id :"+resource);
			vi.inflate(resource, amusementLayout, true);
		} else {
			amusementLayout = (LinearLayout) convertView;
		}

		amusement = (Amusement) getItem(position);
		if (amusement != null) {
			name_textview = (TextView) amusementLayout
					.findViewById(R.id.amusement_name);
			name_textview.setText(amusement.getName());
			
			location_textview=(TextView) amusementLayout.findViewById(R.id.amusement_location);
			location_textview.setText(amusement.getLocation());
			
			phoneList_textview = (TextView) amusementLayout
					.findViewById(R.id.amusement_phone);
			phoneList_textview
					.setText(Tools.phoneNum(amusement.getPhoneList()));
		}
		return amusementLayout;

	}

}
