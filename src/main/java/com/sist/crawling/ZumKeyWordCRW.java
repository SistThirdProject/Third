package com.sist.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ZumKeyWordCRW {
	private final static int SIZE = 10;
	public static String[] getKeyWord(){
		String[] data = new String[SIZE];
		try{
			Document doc = Jsoup.connect("http://zum.com").get();
			Elements words=doc.select("a.d_ready");
			for(int i=0;i<SIZE;i++)
				data[i]=words.get(i).attr("title");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
}
