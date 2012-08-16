package com.stech.model;

import java.util.List;

public class Restaurant extends RankModel {

	private int type;// 1:外卖 2:餐馆 3:食堂
	private List<String> phoneList;// 逻辑用
	private String phoneNum;// 存储用
	private String menuUrl;//饭店菜单url

	private int isSale;
	private int isRest;
	private int isShiTang;

	public static final int ONSALE = 1;
	public static final int REST = 2;
	public static final int SHITANG = 3;

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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
