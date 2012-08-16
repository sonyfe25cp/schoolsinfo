package com.stech.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.stech.model.Traffic;

public class XmlParseForTraffic {
	private String LOGTAG = "XmlParseForTraffic";
	private List<Traffic> trafficList;
	private XmlPullParser parser;

	public XmlParseForTraffic(InputStream inputStream) {
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
			Traffic traffic = null;
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					trafficList = new ArrayList<Traffic>();
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equals("traffic")) {
						traffic = new Traffic();
					} else if (name.equals("id")) {
						value = parser.nextText();
						traffic.setId(Integer.parseInt(value));
					} else if (name.equals("name")) {
						value = parser.nextText();
						traffic.setName(value);
					} else if (name.equals("locationtype")) {
						value = parser.nextText();
						traffic.setLocationType(Integer.parseInt(value));
					} else if (name.equals("stops")) {
						value = parser.nextText();
						traffic.setStops_store(value);
					} else if (name.equals("traffictype")) {
						value = parser.nextText();
						traffic.setTraffictype(Integer.parseInt(value));
					} else if (name.equals("campusId")) {
						value = parser.nextText();
						traffic.setCampusId(Integer.parseInt(value));
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					if (name.equals("traffic")) {
						trafficList.add(traffic);
					}
					break;
				case XmlPullParser.END_DOCUMENT:
					this.trafficList = trafficList;
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

	public List<Traffic> getRestList() {
		return trafficList;
	}

	public void setRestList(List<Traffic> restList) {
		this.trafficList = restList;
	}

}
