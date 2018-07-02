package com.sist.data;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DaumManager {
	
	public static List<KeyWordsVO> daumData()
	{
		List<KeyWordsVO> list=new ArrayList<KeyWordsVO>();
		
		try{
			Document doc=Jsoup.connect("https://www.daum.net/").get();
			
			Elements keywords=doc.select("ol.list_hotissue div.roll_txt span.txt_issue");
			
			int j=1;
			for(int i=0;i<20;i+=2)
			{
				
				KeyWordsVO vo=new KeyWordsVO();
				vo.setNum(j++);
				vo.setKeyword(keywords.get(i).text());
				list.add(vo);

			}
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
	
	
}
