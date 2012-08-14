package com.stech.model;

public class Hotel {
	
	private int id;
	private String name;//名称
	private String location;//大概位置
	private String desc;//描述，价格区间
	
	private float score;//用户评分
	private int defaultRank;//rank值
	private String imgUrl;//图片
	private int campusId;//校区id
	
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
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getDefaultRank() {
		return defaultRank;
	}
	public void setDefaultRank(int defaultRank) {
		this.defaultRank = defaultRank;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getCampusId() {
		return campusId;
	}
	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}
	
	
}
