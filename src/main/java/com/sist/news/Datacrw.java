package com.sist.news;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


public class Datacrw extends Thread {
	
	public String url;
	public String data;

	public Datacrw(String u) {
		url = u;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).get();
			data=doc.select("div.art_body").text();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
