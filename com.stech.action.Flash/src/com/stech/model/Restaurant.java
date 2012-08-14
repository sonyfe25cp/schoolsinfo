package com.stech.model;

import java.util.List;

public class Restaurant {
	
	private int id;
	private String name;
	private String location;
	private String desc;
	private int type;//1:外卖 2:餐馆 3:食堂
	private List<String> phoneList;//逻辑用
	private String phoneNum;//存储用
	private float score;
	private String imgUrl;
	private String menuUrl;
	private int defaultRank; //0:不显示
	private int campusId;//标志所属校区
	
	private int isSale;
	private int isRest;
	private int isShiTang;
	
	public static final int ONSALE=1;
	public static final int REST=2;
	public static final int SHITANG=3;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDefaultRank() {
		return defaultRank;
	}
	public void setDefaultRank(int defaultRank) {
		this.defaultRank = defaultRank;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getCampusId() {
		return campusId;
	}
	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}
	public int getIsSale() {
		return isSale;
	}
	public void setIsSale(int isSale) {
		this.isSale = isSale;
	}
	public int getIsRest() {
		return isRest;
	}
	public void setIsRest(int isRest) {
		this.isRest = isRest;
	}
	public int getIsShiTang() {
		return isShiTang;
	}
	public void setIsShiTang(int isShiTang) {
		this.isShiTang = isShiTang;
	}
	
	

}
