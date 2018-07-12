package com.sist.json;

import java.util.ArrayList;

public class Result {
	private String title;
	private ArrayList<String> keywords;
	private ArrayList<DataOne> data;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	public ArrayList<DataOne> getData() {
		return data;
	}
	public void setData(ArrayList<DataOne> data) {
		this.data = data;
	}
	
}
