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
import com.stech.model.Restaurant;
import com.stech.utils.Tools;

public class RestaurantAdapter extends ArrayAdapter{


	private String LOGTAG="RestaurantAdapter";
	private int resource;
	private Restaurant restaurant;
	private TextView name_textview;
	private TextView phoneList_textview;
	public RestaurantAdapter(Context context, int textViewResourceId,
			List objects) {
		super(context, textViewResourceId, objects);
		this.resource=textViewResourceId;
	}
	
	public Object getItemAtPosition(int position){
		restaurant = (Restaurant) getItem(position);
		return restaurant;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LinearLayout restaurantLayout=null;
		
		if(convertView == null) {
			restaurantLayout = new LinearLayout(getContext()); 
            String inflater = Context.LAYOUT_INFLATER_SERVICE; 
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
//            Logger.i(LOGTAG, "resource id :"+resource);
            vi.inflate(resource, restaurantLayout, true); 
        } else { 
        	restaurantLayout = (LinearLayout)convertView; 
        }
		
		restaurant = (Restaurant) getItem(position);
		if(restaurant!=null){
			name_textview=(TextView) restaurantLayout.findViewById(R.id.restaurant_name);
			name_textview.setText(restaurant.getName());
			phoneList_textview=(TextView)restaurantLayout.findViewById(R.id.restaurant_phoneList);
			phoneList_textview.setText(Tools.phoneNum(restaurant.getPhoneList()));
		}
		return restaurantLayout;
		
	}
	
	
	
	
	

}
