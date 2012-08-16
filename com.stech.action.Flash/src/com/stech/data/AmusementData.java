package com.stech.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.stech.model.Amusement;
import com.stech.parse.XmlParseForAmusement;
import com.stech.utils.Information;
import com.stech.utils.Logger;
import com.stech.utils.NetTools;
import com.stech.utils.Tools;

public class AmusementData {

	private static String LOGTAG="RestaurantData";
	
	public static List<Amusement> getByCampusAndType(Context context,long campusId,int type){
		
		AmusementDB rdb=new AmusementDB(context);
		List<Amusement> amuseList=null;
		if(Tools.isConnectInternet(context)){//本地没有
			Logger.i(LOGTAG, "local amusement is null ");
			amuseList=getAmusementListFromNet(context,campusId, type);
		}else{
			amuseList=rdb.selectList(campusId, type);
			if(amuseList.size()==0){
				Information.setAutoUpdate(context, true);
				amuseList=getAmusementListFromNet(context,campusId, type);
				Information.setAutoUpdate(context, false);
				if(amuseList.size()==0){
					Tools.showToast(context, "暂无数据,请检查网络是否可用~");
				}
			}
		}
		
		return amuseList;
	}
	
	public static List<Amusement> getAmusementListFromNet(Context context,long campusId,int type){
		List<Amusement> amuseList=new ArrayList<Amusement>();
		String url=Information.AMUSEMENTINFOURL;
		Logger.i(LOGTAG, "get amusement from net ");
		InputStream is=null;
		try {
			url=url+"?campusid="+campusId+"&amusetype="+type;
			Logger.i(LOGTAG, url);
			is=NetTools.getContent(url);
			XmlParseForAmusement xpfr=new XmlParseForAmusement(is);
			xpfr.parse();
			amuseList=xpfr.getRestList();
		} catch (IOException e) {
			Logger.e(LOGTAG, "parse amusement from net is error");
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Logger.i(LOGTAG, "restlist from net is "+amuseList.size());
		if(amuseList!=null&&amuseList.size()>0){
			AmusementDB rdb=new AmusementDB(context);
			for(Amusement r:amuseList){
				Logger.i(LOGTAG, "insert all data into db");
				rdb.insertAmusement(r);
			}
		}
		return amuseList;
	}
	
	
	
}
