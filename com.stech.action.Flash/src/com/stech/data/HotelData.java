package com.stech.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.stech.model.Hotel;
import com.stech.parse.XmlParseForHotel;
import com.stech.utils.Information;
import com.stech.utils.Logger;
import com.stech.utils.NetTools;
import com.stech.utils.Tools;

public class HotelData {

	private static String LOGTAG = "RestaurantData";

	public static List<Hotel> getByCampusAndType(Context context, long campusId) {

		HotelDB rdb = new HotelDB(context);
		List<Hotel> hotelList = null;
		if (Tools.isConnectInternet(context)) {// 本地没有
			Logger.i(LOGTAG, "local hotel is null ");
			hotelList = getHotelListFromNet(context, campusId);
		} else {
			hotelList = rdb.selectList(campusId);
			if (hotelList.size() == 0) {
				Information.setAutoUpdate(context, true);
				hotelList = getHotelListFromNet(context, campusId);
				Information.setAutoUpdate(context, false);
				if (hotelList.size() == 0) {
					Tools.showToast(context, "暂无数据,请检查网络是否可用~");
				}
			}
		}

		return hotelList;
	}

	public static List<Hotel> getHotelListFromNet(Context context, long campusId) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		String url = Information.HOTELINFOURL;
		Logger.i(LOGTAG, "get hotel from net ");
		InputStream is = null;
		try {
			url = url + "?campusid=" + campusId;
			Logger.i(LOGTAG, url);
			is = NetTools.getContent(url);
			XmlParseForHotel xpfr = new XmlParseForHotel(is);
			xpfr.parse();
			hotelList = xpfr.getRestList();
		} catch (IOException e) {
			Logger.e(LOGTAG, "parse hotel from net is error");
			e.printStackTrace();
		} finally {
			try {
				if(is!=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Logger.i(LOGTAG, "hotellist from net is " + hotelList.size());
		if (hotelList != null && hotelList.size() > 0) {
			HotelDB rdb = new HotelDB(context);
			for (Hotel r : hotelList) {
				Logger.i(LOGTAG, "insert all data into db");
				rdb.insertHotel(r);
			}
		}
		return hotelList;
	}
}
