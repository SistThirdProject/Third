package com.sist.data;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NaverManager {

	public List<NaverVO> naverData()
	{
		List<NaverVO> list=new ArrayList<NaverVO>();
		try{
			Document doc=Jsoup.connect("https://www.naver.com/").get();
			Elements keywords=doc.select("span.ah_k");
			for(int i=0;i<10;i++)
			{
				
				NaverVO vo=new NaverVO();
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NaverManager naver=new NaverManager();
		List<NaverVO> list=naver.naverData();
		for(NaverVO vo:list)
		{
			
			System.out.println(vo.getNum()+" "+vo.getKeyword());
		}
	}
	
}
