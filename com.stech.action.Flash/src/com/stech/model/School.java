package com.stech.model;

import java.util.ArrayList;

/**
 * 校园信息
 */
public class School {

	private int id;
	private String name;
	private ArrayList<Campus> campusList;
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("id:"+id+"\n");
		sb.append("name:"+name+"\n");
		for(Campus campus:campusList){
			sb.append("\t"+campus);
		}
		return sb.toString();
	}
	
	
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
	public ArrayList<Campus> getCampusList() {
		return campusList;
	}
	public void setCampusList(ArrayList<Campus> campusList) {
		this.campusList = campusList;
	}
	
	
}
