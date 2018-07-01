package com.sist.blog;

import java.util.List;
import java.util.concurrent.TimeUnit;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;





public class NaverBlog {

	
	
 
    
    /*public static void main(String[] args) 
    { 
    	//�겕濡� �뱶�씪�씠踰� �뙆�씪 寃쎈줈�꽕�젙
        System.setProperty("webdriver.chrome.driver", "/home/sist/selenium/chromedriver"); 
        //System.setProperty("webdriver.chrome.driver", "c:\\download\\chromedriver.exe");
        
        // �겕濡� �뱶�씪�씠踰� 媛앹껜 �깮�꽦
     	 WebDriver driver = new ChromeDriver();
     	
     	WebDriverWait wait = new WebDriverWait(driver, 10);
     	WebElement element = wait.Until(ExpectedConditions.elementToBeClickable(By.id("someid")));		
     	//blog �젒�냽
     	 //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
 		driver.get("https://blog.naver.com/PostList.nhn?blogId=ocublog&widgetTypeCall=true&directAccess=true&logNo=221308619983");
 		//driver.get("http://news.naver.com/main/list.nhn?mode=LPOD&mid=sec&sid1=001&sid2=140&oid=001&isYeonhapFlash=Y&aid=0010181085");
 		long start=System.currentTimeMillis();
 		System.out.println(start);
 		
 		try {
			Thread.sleep(2000);
			
			long end=System.currentTimeMillis();
			System.out.println(end);
			System.out.println("실행시간:"+(end - start)/1000.0+"초");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
 		String txt =driver.getPageSource();
 		System.out.println();
 		WebElement c=driver.findElement(By.cssSelector("frame#mainFrame"));
 
 		c.click();
 		
 		//�젒�냽�븳 �궗�씠�듃�쓽 �겢�옒�뒪紐낆씠 �씪移섑븯�뒗 寃� 媛��졇�삤湲�
 		
 		List<WebElement> element = driver.findElements(By.cssSelector("p.se_textarea"));
 		//List<WebElement> element = driver.findElements(By.cssSelector("strong"));
 		//List<WebElement> element = driver.findElements(By.tagName("h3"));
 		//List<WebElement> element = driver.findElements(By.cssSelector("#mainFrame span.dairy.aged div.se_textView"));
 		for(WebElement s:element)
 		{
 			System.out.println(s.getText());
 		}
 		
 		String element = driver.findElement(By.id("postListBody")).getText();
 		System.out.println(element);
 		System.out.println("Crawling End...");
    }*/

	
}
