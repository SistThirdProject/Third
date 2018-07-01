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

	public static void run(String keyword)
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
            FileWriter fw=new FileWriter("c:/data/search.xml");
            fw.write(response.toString());
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static void xmlParser()
	{
		try{
				//xml�뙆�씪�쓽 root�겢�옒�뒪
				JAXBContext jc=JAXBContext.newInstance(Rss.class);
				
				//xml �뙆�씪�쓣 java濡� �씫�뼱�삤湲�
				Unmarshaller un=jc.createUnmarshaller();
				
				Rss rss=(Rss)un.unmarshal(new File("/home/sist/blog/search.xml"));
				
				List<Item> list=rss.getChannel().getItem();
				String linkData="";
				String link="";
				for(Item i:list)
				{
					//data+=i.getLink()+"\n";
					//http://blog.naver.com/ckwldud1?Redirect=Log&logNo=221308325174
					//�쐞 二쇱냼瑜� �븘�옒 二쇱냼濡� 蹂�寃쏀빐�꽌 ���옣
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
				//�궎�썙�뱶濡� 寃��깋�븳 釉붾줈洹� 湲��뱾�쓽 link媛믪쓣 txt�뙆�씪濡� ���옣
				FileWriter fw=new FileWriter("/home/sist/blog/linkData.txt");
				fw.write(linkData);
				fw.close();
				
		}catch(Exception ex){}
	}
	
	

}
