package com.sist.data;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NaverManager {

	public static List<KeyWordsVO> naverData()
	{
		List<KeyWordsVO> list=new ArrayList<KeyWordsVO>();
		try{
			Document doc=Jsoup.connect("https://www.naver.com/").get();
			Elements keywords=doc.select("span.ah_k");
			for(int i=0;i<10;i++)
			{
				
				KeyWordsVO vo=new KeyWordsVO();
				vo.setKeyword(keywords.get(i).text());
				vo.setNum(i+1);
				list.add(vo);
			}
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
	
	
}
