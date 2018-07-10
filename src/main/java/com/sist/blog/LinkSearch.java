package com.sist.blog;


import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.mongodb.BlogVO;




public class LinkSearch {

	public static List<BlogVO> run(List<Item> linkList)
	{
		
		List<BlogVO> list=new ArrayList<BlogVO>();
		
		for(Item links:linkList)
		{
			try{
				//링크를 조합해서 새로운 링크로 들어가야 블로그 데이터 긁기가 가능
				Document pageSource=Jsoup.connect(links.getLink()).get();
				Element link=pageSource.select("#mainFrame").first();
				
				String src=link.attr("src");
		 		String pageNo=links.getLink().substring(links.getLink().indexOf("?"));
		 		String page=src+pageNo;
		 		
		 		//최종 조합된 링크로 들어가서 blogData 가져오기
		 		pageSource=Jsoup.connect("https://blog.naver.com"+page).get();
		 		
		 		//Element dataTag=pageSource.select("div#postListBody").first();
		 		Elements dataTag=pageSource.select("p.se_textarea");
		 		String data="";
		 		for(Element e:dataTag)
		 		{
		 			data+=e.text()+"\n";
		 		}
		 		
		 		BlogVO vo=new BlogVO();
		 		vo.setData(data);
		 		vo.setbTitle(links.getTitle());
		 		vo.setbUrl(links.getLink());
		 		vo.setBlogDate(links.getPostdate().getTime());
		 		list.add(vo);
		 		
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		
		}
		return list;
	}
	
}
