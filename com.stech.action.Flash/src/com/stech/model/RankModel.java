package com.stech.model;

/**
 * @author ChenJie
 *
 * 可以用来打分排序的基类
 */
public class RankModel {

	private int id;
	private String name;
	private String desc;
	private float score;//用户评分
	private int campusId;// 标志所属校区
	private String location;
	private int rank; // 0:不显示
	private String imgUrl;
	
	public static final int DEFAULTRANK=3;

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

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
