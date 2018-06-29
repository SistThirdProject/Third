package com.sist.blog;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Channel {

	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}


	
	
}
