package com.sist.data;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DaumManager {
	
	public List<DaumVO> daumData()
	{
		List<DaumVO> list=new ArrayList<DaumVO>();
		
		try{
			Document doc=Jsoup.connect("https://www.daum.net/").get();
			
			Elements keywords=doc.select("ol.list_hotissue div.roll_txt span.txt_issue");
			
			int j=1;
			for(int i=0;i<20;i+=2)
			{
				
				DaumVO vo=new DaumVO();
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
	
	public static void main(String[] args)
	{
		DaumManager daum=new DaumManager();
		daum.daumData();
	}
}
