package com.sist.mongodb;

import java.util.Date;

public class BlogVO {

	private String keyword;
	private String data;
	private long blogDate;
	private String bUrl;
	private String bTitle;
	
	public long getBlogDate() {
		return blogDate;
	}
	public void setBlogDate(long blogDate) {
		this.blogDate = blogDate;
	}
	public String getbUrl() {
		return bUrl;
	}
	public void setbUrl(String bUrl) {
		this.bUrl = bUrl;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
