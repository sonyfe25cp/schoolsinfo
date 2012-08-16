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
import com.stech.model.Hotel;
import com.stech.utils.Tools;

public class HotelAdapter extends ArrayAdapter {

	private String LOGTAG = "HotelAdapter";
	private int resource;
	private Hotel hotel;
	private TextView name_textview;
	private TextView phoneList_textview;

	public HotelAdapter(Context context, int textViewResourceId, List objects) {
		super(context, textViewResourceId, objects);
		this.resource = textViewResourceId;
	}

	public Object getItemAtPosition(int position) {
		hotel = (Hotel) getItem(position);
		return hotel;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout hotelLayout = null;

		if (convertView == null) {
			hotelLayout = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					inflater);
			// Logger.i(LOGTAG, "resource id :"+resource);
			vi.inflate(resource, hotelLayout, true);
		} else {
			hotelLayout = (LinearLayout) convertView;
		}

		hotel = (Hotel) getItem(position);
		if (hotel != null) {
			name_textview = (TextView) hotelLayout
					.findViewById(R.id.hotel_name);
			name_textview.setText(hotel.getName());
			phoneList_textview = (TextView) hotelLayout
					.findViewById(R.id.hotel_phoneList);
			phoneList_textview.setText(Tools.phoneNum(hotel.getPhoneList()));
		}
		return hotelLayout;

	}

}
