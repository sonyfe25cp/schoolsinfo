package com.stech.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.stech.model.Amusement;

public class XmlParseForAmusement {
	private String LOGTAG = "XmlParseForAmusement";
	private List<Amusement> restList;
	private XmlPullParser parser;

	public XmlParseForAmusement(InputStream inputStream) {
		try {
			parser = Xml.newPullParser();
			parser.setInput(inputStream, "UTF-8");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}

	public void parse() {
		try {
			int event = parser.getEventType();
			String name;
			String value;
			Amusement amusement = null;
			List<String> phoneList = null;
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					restList = new ArrayList<Amusement>();
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equals("amusement")) {
						amusement = new Amusement();
					} else if (name.equals("id")) {
						value = parser.nextText();
						amusement.setId(Integer.parseInt(value));
					} else if (name.equals("name")) {
						value = parser.nextText();
						amusement.setName(value);
					} else if (name.equals("location")) {
						value = parser.nextText();
						amusement.setLocation(value);
					} else if (name.equals("desc")) {
						value = parser.nextText();
						amusement.setDesc(value);
					} else if (name.equals("amusetype")) {
						value = parser.nextText();
						amusement.setAmusetype(Integer.parseInt(value));
					} else if (name.equals("phoneList")) {
						phoneList = new ArrayList<String>();
					} else if (name.equals("phone")) {
						value = parser.nextText();
						if (value != null && value.length() > 0) {
							phoneList.add(value);
						}
					} else if (name.equals("score")) {
						value = parser.nextText();
						amusement.setScore(Float.parseFloat(value));
					} else if (name.equals("imgurl")) {
						value = parser.nextText();
						amusement.setImgUrl(value);
					} else if (name.equals("rank")) {
						value = parser.nextText();
						amusement.setRank(Integer.parseInt(value));
					} else if (name.equals("campusId")) {
						value = parser.nextText();
						amusement.setCampusId(Integer.parseInt(value));
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					if (name.equals("phoneList")) {
						if (phoneList != null && phoneList.size() > 0) {
							amusement.setPhoneList(phoneList);
						}
					} else if (name.equals("amusement")) {
						restList.add(amusement);
					}
					break;
				case XmlPullParser.END_DOCUMENT:
					this.restList = restList;
					break;
				default:
					break;
				}
				event = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Amusement> getRestList() {
		return restList;
	}

	public void setRestList(List<Amusement> restList) {
		this.restList = restList;
	}

}
