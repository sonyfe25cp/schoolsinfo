package com.stech.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.stech.model.Restaurant;
import com.stech.utils.Logger;

public class XmlParseForRestaurant {
	private String LOGTAG="XmlParseForRestaurant";
	private List<Restaurant> restList;
	private InputStream inputStream;
	private XmlPullParser parser;
	
	public XmlParseForRestaurant(InputStream inputStream){
		this.inputStream=inputStream;
		try {
			parser=Xml.newPullParser();
			parser.setInput(inputStream, "UTF-8");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}
	
	public void parse(){
		try {
			int event = parser.getEventType();
			String name;
			String value;
			Restaurant restaurant=null;
			List<String> phoneList=null;
			while(event != XmlPullParser.END_DOCUMENT){
				switch (event) {
					case XmlPullParser.START_DOCUMENT:
						restList=new ArrayList<Restaurant>();
						break;
					case XmlPullParser.START_TAG:
						name=parser.getName();
//						Logger.i(LOGTAG, "NAME:"+name);
						if(name.equals("restaurant")){
							restaurant=new Restaurant();
						}else if(name.equals("id")){
							value=parser.nextText();
							restaurant.setId(Integer.parseInt(value));
						}else if(name.equals("name")){
							value=parser.nextText();
							restaurant.setName(value);
						}else if(name.equals("location")){
							value=parser.nextText();
							restaurant.setLocation(value);
						}else if(name.equals("desc")){
							value=parser.nextText();
							restaurant.setDesc(value);
						}else if(name.equals("isSale")){
							value=parser.nextText();
							if(Integer.parseInt(value)==1){
								restaurant.setIsSale(1);
							}
						}else if(name.equals("isRest")){
							value=parser.nextText();
							if(Integer.parseInt(value)==1){
								restaurant.setIsRest(1);
							}
						}else if(name.equals("isShiTang")){
							value=parser.nextText();
							if(Integer.parseInt(value)==1){
								restaurant.setIsShiTang(1);
							}
						}else if(name.equals("phoneList")){
							phoneList=new ArrayList<String>();
						}else if(name.equals("phone")){
							value=parser.nextText();
							phoneList.add(value);
						}else if(name.equals("score")){
							value=parser.nextText();
							restaurant.setScore(Float.parseFloat(value));
						}else if(name.equals("imgurl")){
							value=parser.nextText();
							restaurant.setImgUrl(value);
						}else if(name.equals("menuurl")){
							value=parser.nextText();
							restaurant.setMenuUrl(value);
						}else if(name.equals("defaultRank")){
							value=parser.nextText();
							restaurant.setDefaultRank(Integer.parseInt(value));
						}else if(name.equals("campusId")){
							value=parser.nextText();
							restaurant.setCampusId(Integer.parseInt(value));
						}
						break;
					case XmlPullParser.END_TAG:
						name=parser.getName();
						if(name.equals("phoneList")){
							restaurant.setPhoneList(phoneList);
						}else if(name.equals("restaurant")){
							restList.add(restaurant);
						}
						break;
					case XmlPullParser.END_DOCUMENT:
						this.restList=restList;
						break;
					default:
						break;
				}
				event = parser.next();
			}
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Restaurant> getRestList() {
		return restList;
	}

	public void setRestList(List<Restaurant> restList) {
		this.restList = restList;
	}
	

}
