package com.stech.custom;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.stech.R;
import com.stech.custom.AsyncRestaurantLoader.RestaurantListCallBack;
import com.stech.model.Restaurant;
import com.stech.utils.Logger;

public class RestaurantCustomLayout implements TabContentFactory{
	
	private String LOGTAG="restaurantCustomLayout";

	private Activity activity=null;
	private  LayoutInflater inflaterHelper;
	private LinearLayout linearLayout;
	private ListView listView;
	private Dialog downloading;
	private Dialog restaurant_details;
	private int RESTAURANT_DETAILS_DIALOG=1;
	
	public RestaurantCustomLayout(Activity activity){
        this.activity = activity;   
        inflaterHelper = activity.getLayoutInflater();   
	}
	@Override
	public View createTabContent(String tag) {
		// TODO Auto-generated method stub
		return getTabContent(tag);
	}
	public View getTabContent(String arg){
//		activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);   //标题栏的布局
		linearLayout=new LinearLayout(activity);
		inflaterHelper.inflate(R.layout.restaurant, linearLayout,true);
		if(arg.equals("outsale")){
			Logger.i(LOGTAG, "outsale click~~");
			flashRestaurantList(Restaurant.ONSALE);
		}else if(arg.equals("rest")){
			Logger.i(LOGTAG, "rest click~~");
			flashRestaurantList(Restaurant.REST);
		}else if(arg.equals("shitang")){
			Logger.i(LOGTAG, "shitang click~~");
			flashRestaurantList(Restaurant.SHITANG);
		}
		return linearLayout;
	}
	
	public void flashRestaurantList(int type){
		listView=(ListView)linearLayout.findViewById(R.id.restaurant_list);
		AsyncRestaurantLoader asyncRestaurantLoader=new AsyncRestaurantLoader(activity);
		ListAdapter adapter=asyncRestaurantLoader.loadRestaurantListAdapter(type, new RestaurantListCallBack(){
			@Override
			public void tuanListLoaded(RestaurantAdapter adapter) {
				listView.setAdapter(adapter);
				downloading.cancel();
			}
		});
		if(adapter.getCount()==0){
			downloading=downloadingDialog(activity);
			downloading.show();
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView=(ListView) arg0;
				Restaurant click_item=(Restaurant)listView.getItemAtPosition(arg2);
				Dialog details_dialog=boomDetailsDialog(activity, click_item);
				details_dialog.show();
			}
		});
	}
	private Dialog downloadingDialog(Context context){
		ProgressDialog dialog = ProgressDialog.show(context, "", 
				activity.getResources().getText(R.string.downloading), true);
		dialog.setCancelable(false);
		return dialog;
	}

	/**
	 * 点击条目时候弹出详细对话框
	 * @param context
	 * @param restaurant
	 * @return
	 */
	public Dialog boomDetailsDialog(final Context context,final Restaurant restaurant){
		LayoutInflater factory = LayoutInflater.from(context);
        final View rest_details_View = factory.inflate(R.layout.rest_details, null);
        RatingBar ratingBar=(RatingBar)rest_details_View.findViewById(R.id.restaurant_details_star);
        ratingBar.setMax(5);
        ratingBar.setRating(restaurant.getScore());
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				final int numStars = ratingBar.getNumStars();
				if (ratingBar.getNumStars() != numStars) {
					ratingBar.setNumStars(numStars);
		        }
		        if (ratingBar.getRating() != rating) {
		        	ratingBar.setRating(rating);
		        }
		        final float ratingBarStepSize = ratingBar.getStepSize();
		        if (ratingBar.getStepSize() != ratingBarStepSize) {
		        	ratingBar.setStepSize(ratingBarStepSize);
		        }
			}
		});
        
        TextView desc_view=(TextView) rest_details_View.findViewById(R.id.restaurant_details_desc);
        desc_view.setText(restaurant.getDesc());
        
        Dialog dialog= new AlertDialog.Builder(context)
        .setTitle(restaurant.getName())
        .setView(rest_details_View)
        .setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	Intent myIntentDial = new Intent("android.intent.action.CALL", Uri.parse("tel:" + restaurant.getPhoneList().get(0))); 
            	context.startActivity(myIntentDial);  
            }
        })
        .setNegativeButton("看看别的", 
       	new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	
            	}
            }
        ).create();
        return dialog;
	}

}
