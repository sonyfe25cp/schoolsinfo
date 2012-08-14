package com.stech.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.stech.model.Campus;
import com.stech.model.School;
import com.stech.utils.Logger;

public class XmlParseForSchools {
	
	private final static String LOGTAG="PullParserForSchools";
	private XmlPullParser parser;
	private InputStream inputStream;
	private List<School> schoolList=null;
	
	public XmlParseForSchools(){
		
	}
	
	public XmlParseForSchools(InputStream inputStream){
		this.inputStream=inputStream;
		parser=Xml.newPullParser();
		try {
			parser.setInput(inputStream, "UTF-8");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
 	}
	
	public void parse(){
		try {
			Logger.i(LOGTAG, "begin to parse");
			int event = parser.getEventType();
			String value=null;
			String name=null;
			School school=null;
			ArrayList<Campus> campusList=null;
			Campus campus=null;
			String attr=null;
			while(event!=XmlPullParser.END_DOCUMENT){
//				Logger.i(LOGTAG, "event : "+event);
				switch (event) {
					case XmlPullParser.START_TAG:
						name=parser.getName();
//						Logger.i(LOGTAG, "name:"+name);
						if(name.equals("school")){
							school=new School();
							campusList=new ArrayList<Campus>();
							attr=parser.getAttributeValue(0);
//							Logger.i(LOGTAG, "attr:"+attr);
							school.setName(attr);
							attr=parser.getAttributeValue(1);
//							Logger.i(LOGTAG, "attr:"+attr);
							school.setId(Integer.parseInt(attr));
						}else if(name.equals("campus")){
							campus=new Campus();
							attr=parser.getAttributeValue(0);
//							Logger.i(LOGTAG, "attr of campus:"+attr);
							campus.setId(Integer.parseInt(attr));
							value=parser.nextText();
//							Logger.i(LOGTAG, "value:"+value);
							campus.setCampus_name(value);
						}
						break;
					case XmlPullParser.END_TAG:
						name = parser.getName();
						if(name.equals("school")){
							school.setCampusList(campusList);
							schoolList.add(school);
						}else if(name.equals("campus")){
							campusList.add(campus);
						}
						break;
					case XmlPullParser.START_DOCUMENT:
						schoolList=new ArrayList<School>();
						break;
					case XmlPullParser.END_DOCUMENT:
						break;
	
					default:
						break;
				}
				event=parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<School> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<School> schoolList) {
		this.schoolList = schoolList;
	}

}
