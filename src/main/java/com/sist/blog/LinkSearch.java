package com.sist.blog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class LinkSearch {

	public static String run(List<String> linkList)
	{
		
		String data="";
		for(String links:linkList)
		{
		try{
			System.out.println(links);
			Document pageSource=Jsoup.connect(links).get();
			
			Element link=pageSource.select("#mainFrame").first();
			
			String src=link.attr("src");
	 		System.out.println(src);
	 		
	 		String pageNo=links.substring(links.indexOf("?"));
	 		
	 		String page=src+pageNo;
	 		System.out.println("https://blog.naver.com"+page);
	 	
	 		pageSource=Jsoup.connect("https://blog.naver.com"+page).get();
	 		
	 		Elements dataTag=pageSource.select("div#postListBody");
	 		
	 		data+=dataTag.text()+"\n";
	 		System.out.println(dataTag.text()+"\n");
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		}
		return data;
	}
	
}
