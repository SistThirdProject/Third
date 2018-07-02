package com.sist.blog;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.*;

public class XmlParser {
	
	public static List<String> run(String fileLoc)
	{
		List<String> linkList=new ArrayList<String>();
		try{
			
			//xml�뙆�씪�쓽 root�겢�옒�뒪
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			
			//xml �뙆�씪�쓣 java濡� �씫�뼱�삤湲�
			Unmarshaller un=jc.createUnmarshaller();
			
			//Rss rss=(Rss)un.unmarshal(new File("/home/sist/blog/search.xml"));
			Rss rss=(Rss)un.unmarshal(new File(fileLoc));
			
			List<Item> list=rss.getChannel().getItem();
			
			String link="";
			for(Item i:list)
			{
				
				
				link=i.getLink();
				
				//System.out.println(link.substring(7,11));
				if(link.substring(7,17).equals("blog.naver"))
				{
					
				link=link.substring(0,link.indexOf("?")+1)+link.substring(link.lastIndexOf("&")+1);
				
				linkList.add(link);
				}
			}
			
			
		}catch(Exception ex){
			System.out.println("parser �삤瑜�");
		}
		return linkList;
	}
}
