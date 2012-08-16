package com.stech.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.stech.model.Traffic;
import com.stech.parse.XmlParseForTraffic;
import com.stech.utils.Information;
import com.stech.utils.Logger;
import com.stech.utils.NetTools;
import com.stech.utils.Tools;

public class TrafficData {

	private static String LOGTAG = "TrafficData";

	public static List<Traffic> getByCampusAndType(Context context,
			long campusId, int traffictype) {

		TrafficDB rdb = new TrafficDB(context);
		List<Traffic> hotelList = null;
		if (Tools.isConnectInternet(context)) {// 本地没有
			Logger.i(LOGTAG, "local hotel is null ");
			hotelList = getTrafficListFromNet(context, campusId, traffictype);
		} else {
			hotelList = rdb.selectList(campusId, traffictype);
			if (hotelList.size() == 0) {
				Information.setAutoUpdate(context, true);
				hotelList = getTrafficListFromNet(context, campusId,
						traffictype);
				Information.setAutoUpdate(context, false);
				if (hotelList.size() == 0) {
					Tools.showToast(context, "暂无数据,请检查网络是否可用~");
				}
			}
		}

		return hotelList;
	}

	public static List<Traffic> getTrafficListFromNet(Context context,
			long campusId, int traffictype) {
		List<Traffic> hotelList = new ArrayList<Traffic>();
		String url = Information.TRAFFICINFOURL;
		Logger.i(LOGTAG, "get hotel from net ");
		InputStream is = null;
		try {
			url = url + "?campusid=" + campusId + "&traffictype="+ traffictype;
			Logger.i(LOGTAG, url);
			is = NetTools.getContent(url);
			XmlParseForTraffic xpfr = new XmlParseForTraffic(is);
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
			TrafficDB rdb = new TrafficDB(context);
			for (Traffic r : hotelList) {
				Logger.i(LOGTAG, "insert all data into db");
				rdb.insertTraffic(r);
			}
		}
		return hotelList;
	}

}
