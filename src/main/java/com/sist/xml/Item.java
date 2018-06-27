package com.sist.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Item implements Serializable{

	private String title;
	private String link;
	private String author;
	private String category;
	private String pubdate;
	private String description;

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


	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}
	@XmlElement
	public void setCategory(String category) {
		this.category = category;
	}

	public String getPubdate() {
		return pubdate;
	}

	@XmlElement
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	

}
