package com.stech.model;

import java.util.List;

public class Hotel extends RankModel{
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
	
}
