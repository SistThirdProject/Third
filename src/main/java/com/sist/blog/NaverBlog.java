package com.sist.blog;

import java.util.List;
import java.util.concurrent.TimeUnit;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class NaverBlog {

	
	/*private static WebDriver driver;
    String Title = null;
    String URL = null;
    String alertText = "";
 
    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/sist/data/chromedriver.exe"); //크롬 드라이버 파일 경로설정
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //응답시간 5초설정
        driver.get("http://blog.naver.com/ocublog?logNo=221308619983");  //접속할 사이트
    }
   //blog 본문 class명 - se_component se_paragraph default 
    
    @Test
    public static void blogCrwl () throws Exception {
         
    	WebElement element = driver.findElement(By.className("se_component se_paragraph default"));    
        //blog 본문 내용 찾기
    	String blogContext=element.getText();
        System.out.println(blogContext);
 
    }
    
 
    @AfterClass
    public static void tearDown() throws Exception {
    	
        driver.quit();
    }*/
 
    
    public static void main(String[] args) 
    { 
    	//크롬 드라이버 파일 경로설정
        System.setProperty("webdriver.chrome.driver", "/home/sist/selenium/chromedriver"); 
        
        // 크롬 드라이버 객체 생성
     	 WebDriver driver = new ChromeDriver();
     	
     	//blog 접속
 		driver.get("http://blog.naver.com/ocublog?logNo=221308619983");
 		
 		//접속한 사이트의 클래스명이 일치하는 것 가져오기
 		List<WebElement> element = driver.findElements(By.className("se_editView se_textView se_textarea"));
 		
 		for(WebElement s:element)
 		{
 			System.out.println(s.getText());
 		}
 		
 		/*String element = driver.findElement(By.id("postListBody")).getText();
 		System.out.println(element);*/
 		System.out.println("Crawling End...");
    }

	
}
