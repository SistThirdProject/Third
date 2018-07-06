package com.sist.vo;


public class NewsVO {
	private long time;
	private int rank;
	private String title;
	private String link;
	private String keyword;
	private String newsdata;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getKeyWord() {
		return keyword;
	}
	public void setKeyWord(String keyWord) {
		this.keyword = keyWord;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getNewsdata() {
		return newsdata;
	}
	public void setNewsdata(String newsdata) {
		this.newsdata = newsdata;
	}
	
}
