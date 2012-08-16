package com.stech.model;

import java.util.List;

public class Amusement extends RankModel{


	public static final int KTV=1;
	public static final int THEATER=2;
	public static final int MALL=3;
	private int amusetype;
	
	private List<String> phoneList;// 逻辑用
	private String phoneNum;// 存储用
	
	
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public static int getKtv() {
		return KTV;
	}
	public static int getTheater() {
		return THEATER;
	}
	public static int getMall() {
		return MALL;
	}
	public int getAmusetype() {
		return amusetype;
	}
	public void setAmusetype(int amusetype) {
		this.amusetype = amusetype;
	}
	
}
