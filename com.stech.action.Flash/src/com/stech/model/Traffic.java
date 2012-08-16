package com.stech.model;

import java.util.List;

public class Traffic {

	private static final int East = 1;
	private static final int West = 2;
	private static final int North = 3;
	private static final int South = 4;
	private static final int North_East = 5;
	private static final int North_West = 6;
	private static final int South_East = 7;
	private static final int South_West = 8;

	private int id;
	private String name;// 站名
	private int locationType;// 位置
	private int traffictype;// 公交or地铁or什么
	private List<String> stops;
	private String stops_store;//存储用
	private int campusId;// 标志所属校区

	private static final int BUS = 1;
	private static final int SUBWAY = 2;

	public static String getTrafficType(int traffictype){
		String traffic_name="";
		switch(traffictype){
		case 1:
			traffic_name="公交";
			break;
		case 2:
			traffic_name="地铁";
			break;
		default:
			break;
		}
		return traffic_name;
	}
	
	
	public static String getLocationName(int locationtype) {
		String location_name = "";
		switch (locationtype) {
		case 1:
			location_name = "东门";
			break;
		case 2:
			location_name = "西门";
			break;
		case 3:
			location_name = "北门";
			break;
		case 4:
			location_name = "南门";
			break;
		case 5:
			location_name = "东北门";
			break;
		case 6:
			location_name = "西北门";
			break;
		case 7:
			location_name = "东南门";
			break;
		case 8:
			location_name = "西南门";
			break;
		default:
			break;
		}
		return location_name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getLocationType() {
		return locationType;
	}

	public void setLocationType(int locationType) {
		this.locationType = locationType;
	}

	public int getTraffictype() {
		return traffictype;
	}

	public void setTraffictype(int traffictype) {
		this.traffictype = traffictype;
	}


	public List<String> getStops() {
		return stops;
	}

	public void setStops(List<String> stops) {
		this.stops = stops;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public String getStops_store() {
		return stops_store;
	}

	public void setStops_store(String stops_store) {
		this.stops_store = stops_store;
	}

}
