package com.stech.model;

public class Campus {

	private int id;
	private String campus_name;
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("id:"+id+"\n");
		sb.append("campus_name:"+campus_name+"\n");
		return sb.toString();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCampus_name() {
		return campus_name;
	}
	public void setCampus_name(String campus_name) {
		this.campus_name = campus_name;
	}
	
	
}
