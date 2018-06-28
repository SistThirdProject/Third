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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class NaverBlog {

	
	
 
    
    public static void main(String[] args) 
    { 
    	//�겕濡� �뱶�씪�씠踰� �뙆�씪 寃쎈줈�꽕�젙
        //System.setProperty("webdriver.chrome.driver", "/home/sist/selenium/chromedriver"); 
        System.setProperty("webdriver.chrome.driver", "c:\\download\\chromedriver.exe");
        
        // �겕濡� �뱶�씪�씠踰� 媛앹껜 �깮�꽦
     	 WebDriver driver = new ChromeDriver();
     	
     	/*WebDriverWait wait = new WebDriverWait(driver, 10);
     	
     	WebElement element = wait.Until(ExpectedConditions.elementToBeClickable(By.id("someid")));*/		
     	//blog �젒�냽
 		driver.get("http://blog.naver.com/ocublog?logNo=221308619983");
 		long start=System.currentTimeMillis();
 		System.out.println(start);
 		
 		try {
			Thread.sleep(20000);
			
			long end=System.currentTimeMillis();
			System.out.println(end);
			System.out.println("실행시간:"+(end - start)/1000.0+"초");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
 		
 		
 		//�젒�냽�븳 �궗�씠�듃�쓽 �겢�옒�뒪紐낆씠 �씪移섑븯�뒗 寃� 媛��졇�삤湲�
 		//List<WebElement> element = driver.findElements(By.className("se_textarea"));
 		//List<WebElement> element = driver.findElements(By.tagName("h3"));
 		List<WebElement> element = driver.findElements(By.cssSelector("#mainFrame span.dairy.aged div.se_textView"));
 		for(WebElement s:element)
 		{
 			System.out.println(s.getText());
 		}
 		
 		/*String element = driver.findElement(By.id("postListBody")).getText();
 		System.out.println(element);*/
 		System.out.println("Crawling End...");
    }

	
}
