package com.sist.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sist.vo.NewsVO;
import com.sist.xml.Item;

public class NewsDataCRW extends Thread {
	private Item item;
	private NewsVO vo;

	public NewsDataCRW(Item item) {
		this.item = item;
		vo = new NewsVO();
		
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(item.getLink()).get();
			String text = doc.select("body").text();
			if(!text.equals(""))
				item.setDescription(text);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Item getItem() {
		return item;
	}

	public NewsVO getVo() {
		return vo;
	}

}
