package com.sist.blog;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String link;
	private String title;
	private Date postdate;
	
	public Date getPostdate() {
		return postdate;
	}
	@XmlElement
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
