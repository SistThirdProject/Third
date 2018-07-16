package com.sist.mongodb;



public class BlogVO {

	private String keyword;
	private String newsdata;
	
	private long time;
	private String link;
	private String title;
	
	public String getNewsdata() {
		return newsdata;
	}
	public void setNewsdata(String newsdata) {
		this.newsdata = newsdata;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
