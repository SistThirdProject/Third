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

	public static void run(List<String> linkList)
	{
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
	 		
	 		Elements dataTag=pageSource.select("div.se_textView");
	 		
	 		System.out.println(dataTag.text());
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		}
	}
	/*
	public static List<String> linkReader()
	{
		List<String> linkList=new ArrayList<String>();
		try{
		
		File file=new File("/home/sist/blog/linkData.txt");
		FileReader fileReader=new FileReader(file);
		BufferedReader lineReader=new BufferedReader(fileReader);
		String line="";
		//�씫�쓣 �씪�씤�씠(text媛�) �뾾�쓣�븣源뚯�
		while((line=lineReader.readLine())!=null)
		{
			//System.out.println(line);
			linkList.add(line);
		}
		lineReader.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return linkList;
	}*/
	
	/*public static void run(String link)
	{
		//chrome driver�쐞移� �꽕�젙
        System.setProperty("webdriver.chrome.driver", "/home/sist/selenium/chromedriver"); 
        //System.setProperty("webdriver.chrome.driver", "c:\\download\\chromedriver.exe");
        
        // chrome driver濡� chrome �떎
     	 WebDriver driver = new ChromeDriver();
     	
     	 //�뿰寃고븷 留곹겕 �엯�젰
 		driver.get(link);
 		long start=System.currentTimeMillis();
 		System.out.println(start);
 		
 		try {
			Thread.sleep(2000);
			
			long end=System.currentTimeMillis();
			System.out.println(end);
			System.out.println("�떎�뻾�떆媛�:"+(end - start)/1000.0+"珥�");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

 		
 		WebElement element = driver.findElement(By.cssSelector("#mainFrame"));
 		
 		String s=element.getAttribute("src");
 		System.out.println(s);
 		System.out.println(link.substring(link.indexOf("?")));
 		String pageNo=link.substring(link.indexOf("?"));
 		System.out.println(s+pageNo);
 		String page=s+pageNo;
 		for(WebElement s:element)
 		{
 			System.out.println(s.getText());
 		}
 	
 		driver.get(page);
 		
 		List<WebElement> elementList = driver.findElements(By.cssSelector("div.se_textView"));
 		for(WebElement a:elementList)
 		{
 			System.out.println(a.getText());
 		}
 		System.out.println("Crawling End...");
	}*/
}
