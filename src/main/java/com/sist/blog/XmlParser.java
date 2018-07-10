package com.sist.blog;

import java.io.File;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



import java.util.*;

public class XmlParser {
	
	public static List<Item> run(String fileLoc)
	{
		List<Item> list=new ArrayList<Item>();
		List<Item> bList=new ArrayList<Item>();
		try{
			
			//xml�뙆�씪�쓽 root�겢�옒�뒪
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			
			//xml �뙆�씪�쓣 java濡� �씫�뼱�삤湲�
			Unmarshaller un=jc.createUnmarshaller();
			
			//Rss rss=(Rss)un.unmarshal(new File("/home/sist/blog/search.xml"));
			Rss rss=(Rss)un.unmarshal(new File(fileLoc));
			
			list=rss.getChannel().getItem();
			
			String link="";
			String title="";
			for(Item i:list)
			{
				
				link=i.getLink();
				
				//System.out.println(link.substring(7,11));
				if(link.substring(7,17).equals("blog.naver"))
				{
					Item item=new Item();
					
				link=link.substring(0,link.indexOf("?")+1)+link.substring(link.lastIndexOf("&")+1);
				item.setLink(link);
				
				title=i.getTitle();
				title=title.replaceAll("<b>", "");
				title=title.replaceAll("</b>", "");
				title=title.replaceAll("&quot;", "");
				title=title.replaceAll("&lt;", "");
				title=title.replaceAll("&gt;", "");
				title=title.replaceAll("&amp;", "");
				/*title=title.replace("<", "");
				title=title.replace("b", "");
				title=title.replace(">", "");
				title=title.replace("/", "");*/
				
				item.setTitle(title);
				item.setPostdate(i.getPostdate());
				bList.add(item);
				}
			}
			
			
		}catch(Exception ex){
			System.out.println("parser �삤瑜�");
		}
		
		return bList;
	}
}
