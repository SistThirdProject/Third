package com.sist.data;

import java.util.*;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.regex.*;
public class NateManager {
	
	public List<NateVO> nateData()
	{
		List<NateVO> list=new ArrayList<NateVO>();
		
		try{
			Document doc=Jsoup.connect("https://search.daum.net/nate?nil_suggest=btn&nil_ch=&rtupcoll=&w=tot&m=&f=&lpp=&DA=SBC&sug=&sq=&o=&sugo=&q=+").get();
			System.out.println(doc);
			
			
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
	
	public static void main(String[] args)
	{
		NateManager nate=new NateManager();
		nate.nateData();
	}
	
}
