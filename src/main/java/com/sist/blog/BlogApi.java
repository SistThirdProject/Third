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

import breeze.macros.expand.exclude;
public class BlogApi {

	public static void run(String keyword,String fileLoc)
	{
		String clientId = "595ky2JhW6gjTFMU2VNQ";//�븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �븘�씠�뵒媛�";
        String clientSecret = "PYobMZvezd";//�븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �떆�겕由욧컪";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&query="+ text; // json 寃곌낵
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+ text; // xml 寃곌낵
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // �젙�긽 �샇異�
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // �뿉�윭 諛쒖깮
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            //xml濡� 寃��깋�븳 寃곌낵媛� ���옣
            //FileWriter fw=new FileWriter("/home/sist/blog/search.xml");
            FileWriter fw=new FileWriter(fileLoc);
            fw.write(response.toString());
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	

}
