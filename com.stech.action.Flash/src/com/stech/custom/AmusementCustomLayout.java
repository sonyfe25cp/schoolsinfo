package com.stech.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.stech.R;
import com.stech.custom.AsyncAmusementLoader.AmusementListCallBack;
import com.stech.model.Amusement;
import com.stech.utils.Logger;

/**
 * @author ChenJie
 * 
 *         自定义娱乐列表
 */
public class AmusementCustomLayout implements TabContentFactory {

	private String LOGTAG = "AmusementCustomLayout";

	private Activity activity = null;
	private LayoutInflater inflaterHelper;
	private LinearLayout linearLayout;
	private ListView listView;
	private Dialog downloading;
	private Dialog restaurant_details;
	private int RESTAURANT_DETAILS_DIALOG = 1;

	public AmusementCustomLayout(Activity activity) {
		this.activity = activity;
		inflaterHelper = activity.getLayoutInflater();
	}

	@Override
	public View createTabContent(String tag) {
		return getTabContent(tag);
	}

	/**
	 * 根据标签来选择下载数据
	 * 
	 * @param arg
	 * @return
	 */
	public View getTabContent(String arg) {
		// activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);
		// //标题栏的布局
		linearLayout = new LinearLayout(activity);
		inflaterHelper.inflate(R.layout.amusement, linearLayout, true);
		if (arg.equals("ktv")) {
			Logger.i(LOGTAG, "outsale click~~");
			flashRestaurantList(Amusement.KTV);
		} else if (arg.equals("theater")) {
			Logger.i(LOGTAG, "rest click~~");
			flashRestaurantList(Amusement.THEATER);
		} else if (arg.equals("mall")) {
			Logger.i(LOGTAG, "shitang click~~");
			flashRestaurantList(Amusement.MALL);
		}
		return linearLayout;
	}

	/**
	 * 刷新当前列表
	 * 
	 * @param type
	 */
	public void flashRestaurantList(int type) {
		listView = (ListView) linearLayout.findViewById(R.id.amusement_list);
		AsyncAmusementLoader asyncAmusementLoader = new AsyncAmusementLoader(
				activity);
		ListAdapter adapter = asyncAmusementLoader.loadAmusementListAdapter(
				type, new AmusementListCallBack() {
					@Override
					public void listLoaded(AmusementAdapter adapter) {
						listView.setAdapter(adapter);
						downloading.cancel();
					}
				});
		if (adapter.getCount() == 0) {
			downloading = downloadingDialog(activity);
			downloading.show();
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView = (ListView) arg0;
				Amusement click_item = (Amusement) listView
						.getItemAtPosition(arg2);
				Dialog details_dialog = boomDetailsDialog(activity, click_item);
				details_dialog.show();
			}
		});
	}

	/**
	 * 下载数据时候的loading
	 * 
	 * @param context
	 * @return
	 */
	private Dialog downloadingDialog(Context context) {
		ProgressDialog dialog = ProgressDialog.show(context, "", activity
				.getResources().getText(R.string.downloading), true);
		dialog.setCancelable(false);
		return dialog;
	}

	/**
	 * 点击条目时候弹出详细对话框
	 * 
	 * @param context
	 * @param amusement
	 * @return
	 */
	public Dialog boomDetailsDialog(final Context context,
			final Amusement amusement) {
		LayoutInflater factory = LayoutInflater.from(context);
		final View rest_details_View = factory.inflate(R.layout.rest_details,
				null);
		/*
		 * 暂时去掉评分功能
		 */
		// RatingBar ratingBar = (RatingBar) rest_details_View
		// .findViewById(R.id.restaurant_details_star);
		// ratingBar.setMax(5);
		// ratingBar.setRating(restaurant.getScore());
		// ratingBar.setOnRatingBarChangeListener(new
		// OnRatingBarChangeListener() {
		//
		// @Override
		// public void onRatingChanged(RatingBar ratingBar, float rating,
		// boolean fromUser) {
		// final int numStars = ratingBar.getNumStars();
		// if (ratingBar.getNumStars() != numStars) {
		// ratingBar.setNumStars(numStars);
		// }
		// if (ratingBar.getRating() != rating) {
		// ratingBar.setRating(rating);
		// }
		// final float ratingBarStepSize = ratingBar.getStepSize();
		// if (ratingBar.getStepSize() != ratingBarStepSize) {
		// ratingBar.setStepSize(ratingBarStepSize);
		// }
		// /*
		// * 更新评分
		// */
		// }
		// });

		TextView desc_view = (TextView) rest_details_View
				.findViewById(R.id.restaurant_details_desc);
		desc_view.setText(amusement.getDesc());

		Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(amusement.getName()).setView(rest_details_View);
		// Logger.i(LOGTAG, "size:"+restaurant.getPhoneList().size());
		if (amusement.getPhoneList() != null
				&& amusement.getPhoneList().size() > 0) {
			builder.setPositiveButton(R.string.call_phone,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Intent myIntentDial = new Intent(
									"android.intent.action.CALL", Uri
											.parse("tel:"
													+ amusement.getPhoneList()
															.get(0)));
							context.startActivity(myIntentDial);
						}
					});
		}
		builder.setNegativeButton(R.string.cancel_phone,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				});
		Dialog dialog = builder.create();
		// Dialog dialog = new AlertDialog.Builder(context)
		// .setTitle(restaurant.getName())
		// .setView(rest_details_View)
		// .setPositiveButton("拨打电话",
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		// Intent myIntentDial = new Intent(
		// "android.intent.action.CALL", Uri
		// .parse("tel:"
		// + restaurant
		// .getPhoneList()
		// .get(0)));
		// context.startActivity(myIntentDial);
		// }
		// })
		// .setNegativeButton("看看别的",
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		//
		// }
		// }).create();
		return dialog;
	}

}
