package com.sist.news;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Linkcrw extends Thread {
	private String url;
	public ArrayList<String> links;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Linkcrw(String u) {
		url = u;
		links = new ArrayList<String>();
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements els = doc.select("dl.phArtc a");
			for (int i = 0; i < els.size(); i++)
				links.add(els.get(i).attr("href"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
