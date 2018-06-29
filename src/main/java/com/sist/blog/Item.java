package com.sist.blog;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String link;

	public String getLink() {
		return link;
	}

	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
