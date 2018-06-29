package com.sist.blog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkSearch {

	/*
	public static List<String> linkReader()
	{
		List<String> linkList=new ArrayList<String>();
		try{
		
		File file=new File("/home/sist/blog/linkData.txt");
		FileReader fileReader=new FileReader(file);
		BufferedReader lineReader=new BufferedReader(fileReader);
		String line="";
		//읽을 라인이(text가) 없을때까지
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
	
	public static void run(String link)
	{
		//chrome driver위치 설정
        System.setProperty("webdriver.chrome.driver", "/home/sist/selenium/chromedriver"); 
        //System.setProperty("webdriver.chrome.driver", "c:\\download\\chromedriver.exe");
        
        // chrome driver로 chrome 실
     	 WebDriver driver = new ChromeDriver();
     	
     	 //연결할 링크 입력
 		driver.get(link);
 		/*long start=System.currentTimeMillis();
 		System.out.println(start);
 		
 		try {
			Thread.sleep(2000);
			
			long end=System.currentTimeMillis();
			System.out.println(end);
			System.out.println("실행시간:"+(end - start)/1000.0+"초");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}*/

 		
 		WebElement element = driver.findElement(By.cssSelector("#mainFrame"));
 		
 		String s=element.getAttribute("src");
 		System.out.println(s);
 		System.out.println(link.substring(link.indexOf("?")));
 		String pageNo=link.substring(link.indexOf("?"));
 		System.out.println(s+pageNo);
 		String page=s+pageNo;
 		/*for(WebElement s:element)
 		{
 			System.out.println(s.getText());
 		}
 	*/
 		driver.get(page);
 		
 		List<WebElement> elementList = driver.findElements(By.cssSelector("div.se_textView"));
 		for(WebElement a:elementList)
 		{
 			System.out.println(a.getText());
 		}
 		System.out.println("Crawling End...");
	}
}
