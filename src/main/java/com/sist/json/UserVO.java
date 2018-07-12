package com.sist.json;

import java.util.ArrayList;

public class UserVO {
	private String sdate; // 시작 날짜
	private String edate; // 끝나는 날짜
	private String gname; // 그래프에 표시할 주제어
	private String keyword; // 검색할 키워드
	private ArrayList<String> randomkeyword; // 검색할 키워드를 제외하고 나머지 키워드는 랜덤으로 
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public ArrayList<String> getRandomkeyword() {
		return randomkeyword;
	}
	public void setRandomkeyword(ArrayList<String> randomkeyword) {
		this.randomkeyword = randomkeyword;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	
}
