package com.sist.blog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
public class BlogApi {

	public static void run(String keyword)
	{
		String clientId = "595ky2JhW6gjTFMU2VNQ";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "PYobMZvezd";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&query="+ text; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            //xml로 검색한 결과값 저장
            FileWriter fw=new FileWriter("/home/sist/blog/search.xml");
            fw.write(response.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public static void xmlParser()
	{
		try{
				//xml파일의 root클래스
				JAXBContext jc=JAXBContext.newInstance(Rss.class);
				
				//xml 파일을 java로 읽어오기
				Unmarshaller un=jc.createUnmarshaller();
				
				Rss rss=(Rss)un.unmarshal(new File("/home/sist/blog/search.xml"));
				
				List<Item> list=rss.getChannel().getItem();
				String linkData="";
				String link="";
				for(Item i:list)
				{
					//data+=i.getLink()+"\n";
					//http://blog.naver.com/ckwldud1?Redirect=Log&logNo=221308325174
					//위 주소를 아래 주소로 변경해서 저장
					//http://blog.naver.com/ckwldud1?logNo=221308325174
					
					link=i.getLink();
					
					//System.out.println(link.substring(7,11));
					if(link.substring(7,17).equals("blog.naver"))
					{
						
					link=link.substring(0,link.indexOf("?")+1)+link.substring(link.lastIndexOf("&")+1);
					//System.out.println(link);
					linkData+=link+"\n";
					}
				}
				//https://blog.naver.com
				//키워드로 검색한 블로그 글들의 link값을 txt파일로 저장
				FileWriter fw=new FileWriter("/home/sist/blog/linkData.txt");
				fw.write(linkData);
				fw.close();
				
		}catch(Exception ex){}
	}
	
	

}
