package com.stech.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.stech.model.Hotel;

public class XmlParseForHotel {
	private String LOGTAG = "XmlParseForHotel";
	private List<Hotel> restList;
	private XmlPullParser parser;

	public XmlParseForHotel(InputStream inputStream) {
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
			Hotel hotel = null;
			List<String> phoneList = null;
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					restList = new ArrayList<Hotel>();
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equals("hotel")) {
						hotel = new Hotel();
					} else if (name.equals("id")) {
						value = parser.nextText();
						hotel.setId(Integer.parseInt(value));
					} else if (name.equals("name")) {
						value = parser.nextText();
						hotel.setName(value);
					} else if (name.equals("location")) {
						value = parser.nextText();
						hotel.setLocation(value);
					} else if (name.equals("desc")) {
						value = parser.nextText();
						hotel.setDesc(value);
					} else if (name.equals("phoneList")) {
						phoneList = new ArrayList<String>();
					} else if (name.equals("phone")) {
						value = parser.nextText();
						if (value != null && value.length() > 0) {
							phoneList.add(value);
						}
					} else if (name.equals("score")) {
						value = parser.nextText();
						hotel.setScore(Float.parseFloat(value));
					} else if (name.equals("imgurl")) {
						value = parser.nextText();
						hotel.setImgUrl(value);
					} else if (name.equals("rank")) {
						value = parser.nextText();
						hotel.setRank(Integer.parseInt(value));
					} else if (name.equals("campusId")) {
						value = parser.nextText();
						hotel.setCampusId(Integer.parseInt(value));
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					if (name.equals("phoneList")) {
						if (phoneList != null && phoneList.size() > 0) {
							hotel.setPhoneList(phoneList);
						}
					} else if (name.equals("hotel")) {
						restList.add(hotel);
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

	public List<Hotel> getRestList() {
		return restList;
	}

	public void setRestList(List<Hotel> restList) {
		this.restList = restList;
	}

}
