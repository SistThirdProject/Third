package com.sist.blog;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String link;
	private String title;
	
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
