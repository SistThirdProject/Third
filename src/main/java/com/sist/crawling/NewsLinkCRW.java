package com.sist.crawling;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.sist.xml.*;

public class NewsLinkCRW extends Thread {
	private String item = "http://newssearch.naver.com/search.naver?where=rss&query=";
	private List<Item> list;

	public List<Item> getList() {
		return list;
	}

	public NewsLinkCRW(String item) {
		this.item += item;
	}

	@Override
	public void run() {
		try {
			URL url = new URL(item);
			JAXBContext jc = JAXBContext.newInstance(Rss.class);
			Unmarshaller un = jc.createUnmarshaller();
			Rss rss = (Rss) un.unmarshal(url);
			list = rss.getChannel().getItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
