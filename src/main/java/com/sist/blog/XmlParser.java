package com.sist.blog;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.*;

public class XmlParser {
	
	public static List<String> run()
	{
		List<String> linkList=new ArrayList<String>();
		try{
			
			//xml�뙆�씪�쓽 root�겢�옒�뒪
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			
			//xml �뙆�씪�쓣 java濡� �씫�뼱�삤湲�
			Unmarshaller un=jc.createUnmarshaller();
			
			//Rss rss=(Rss)un.unmarshal(new File("/home/sist/blog/search.xml"));
			Rss rss=(Rss)un.unmarshal(new File("c:/data/search.xml"));
			
			List<Item> list=rss.getChannel().getItem();
			
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
				
				linkList.add(link);
				}
			}
			//https://blog.naver.com
			//�궎�썙�뱶濡� 寃��깋�븳 釉붾줈洹� 湲��뱾�쓽 link媛믪쓣 txt�뙆�씪濡� ���옣
			/*FileWriter fw=new FileWriter("/home/sist/blog/"+file+".txt");
			fw.write(linkData);
			fw.close();*/
			
		}catch(Exception ex){
			System.out.println("parser �삤瑜�");
		}
		return linkList;
	}
}
