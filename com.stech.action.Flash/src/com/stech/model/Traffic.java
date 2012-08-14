package com.stech.model;

public class Traffic {
	
	private int id;
	private String name;
	private int location;//地理位置
	
	private String desc;//各种站，用|区分
	
	private int campusId;
private float score;
private int defaultRank;
	
	private static final int East=1;
	private static final int West=2;
	private static final int North=3;
	private static final int South=4;
	private static final int North_East=5;
	private static final int North_West=6;
	private static final int South_East=7;
	private static final int South_West=8;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCampusId() {
		return campusId;
	}
	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}
	
	

}
