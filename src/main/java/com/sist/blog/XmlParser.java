package com.sist.blog;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlParser {
	
	public static String run(String file)
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
			FileWriter fw=new FileWriter("/home/sist/blog/"+file+".txt");
			fw.write(linkData);
			fw.close();
			
		}catch(Exception ex){
			System.out.println("parser 오류");
		}
		return file+".txt";
	}
}
