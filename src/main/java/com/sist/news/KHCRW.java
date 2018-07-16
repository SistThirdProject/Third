package com.sist.news;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class KHCRW {
	public static void main (String[] args) throws IOException
	{
		String totalData=KHCRW.get("북한", 2017, 4);
		
		FileWriter fw=new FileWriter("/home/sist/thdata/kh");
		fw.write(totalData);
		fw.close();
	}
	public static String get(String keyword, int year, int month) {
		List<String> result = new ArrayList<String>();
		String totalData="";
		String start = month < 10 ? year + "0" + month : year + "" + month;
		String end = YearMonth.of(year, month).atEndOfMonth().toString().replaceAll("-", "");
		String d1 = start + "01~" + end;
		int page = 1;

		String url = "http://search.khan.co.kr/search.html?stb=khan&q=" + keyword + "&d1=" + d1 + "&sort=2&pg=";
		try {
			Document doc = Jsoup.connect(url + page).get();
			String count = doc.select("span.search_num").first().text();
			count = count.substring(count.indexOf("총") + 2, count.indexOf("건") - 1);
			count=count.replaceAll(",", "");
			int c = Integer.parseInt(count);
			int size = (int) Math.ceil(c / 10.0);
			ArrayList<String> linklist = new ArrayList<String>();
			ArrayList<Linkcrw> crwlist = new ArrayList<Linkcrw>();
			ArrayList<Datacrw> dcrwlist = new ArrayList<Datacrw>();
			for (int i = 1; i <= size; i++) {
				Linkcrw lcrw = new Linkcrw(url + i);
				crwlist.add(lcrw);
				lcrw.start();
			}
			for (Linkcrw lcrw : crwlist) {
				while (lcrw.isAlive());
				linklist.addAll(lcrw.links);
			}
			for(String link: linklist) {
				Datacrw dcrw = new Datacrw(link);
				dcrwlist.add(dcrw);
				dcrw.start();
			}
			for(Datacrw dcrw : dcrwlist) {
				while (dcrw.isAlive());
				
				totalData+=dcrw.data+"\n";
				totalData=totalData.replace(".", "");
				totalData=totalData.replace("“", "");
				totalData=totalData.replace("”", "");
				totalData=totalData.replace("ㆍ", "");
				totalData=totalData.replace("‘", "");
				totalData=totalData.replace("’", "");
				totalData=totalData.replace("···", "");
				totalData=totalData.replace("(", "");
				totalData=totalData.replace(")", "");
				totalData=totalData.replace("null","");
				
				//result.add(dcrw.data);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return totalData;
	}
}
