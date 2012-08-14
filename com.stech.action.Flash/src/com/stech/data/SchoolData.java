package com.stech.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.stech.model.School;
import com.stech.parse.XmlParseForSchools;
import com.stech.utils.Information;
import com.stech.utils.Logger;
import com.stech.utils.NetTools;
import com.stech.utils.Tools;

public class SchoolData {
	
	private static String LOGTAG="schooldata";
	
	public static List<School> schoolList(Context context){
		SchoolDB sdb=new SchoolDB(context);
		List<School> slist=sdb.selectList();
		if(slist==null || slist.size()==0){
			Tools.showToast(context, "school db is null,get from net");
			Logger.i(LOGTAG, "school db is null,get from net");
			slist=getSchoolListFromNet(context);
		}
		Logger.i(LOGTAG, "school list size :"+ slist.size());
		return slist;
	}
	
	public static void insertSchoolList(Context context,List<School> schoolList){
		Logger.i(LOGTAG, "begin to insert into android db");
		SchoolDB sdb=new SchoolDB(context);
		for(School tmp:schoolList){
			sdb.insert(tmp);
			Logger.i(LOGTAG,tmp.getId() +" is inserted into db");
		}
	}
	
	public static List<School> getSchoolListFromNet(Context context){
		String url=Information.SCHOOLINFOURL;
		InputStream is=null;
		List<School> sList=new ArrayList<School>();
		Logger.i(LOGTAG, "begin to get from net");
		try {
			is=NetTools.getContent(url);
			XmlParseForSchools xps=new XmlParseForSchools(is);
			xps.parse();
			sList=xps.getSchoolList();
		} catch (IOException e) {
			Logger.i(LOGTAG, "parse from net error");
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Logger.i(LOGTAG, "parse slist over, "+sList.size());
		if(sList!=null && sList.size()>0){
			Logger.i(LOGTAG, "insert all data into db");
			insertSchoolList(context, sList);
		}
		return sList;
	}

}
