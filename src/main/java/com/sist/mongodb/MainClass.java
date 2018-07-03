package com.sist.mongodb;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;

import com.sist.blog.BlogApi;
import com.sist.blog.Item;
import com.sist.blog.LinkSearch;
import com.sist.blog.XmlParser;
import com.sist.data.KeyWordsVO;
import com.sist.data.NaverManager;

@Component
public class MainClass {
	
	@Autowired
	private BlogDAO dao;
	
	
	public static void main(String[] args)
	{
		try{
			
			ApplicationContext app=new ClassPathXmlApplicationContext("application-mongo.xml");
			
			MainClass main=(MainClass)app.getBean("mainClass");
			
			List<KeyWordsVO> list=NaverManager.naverData();
			//인기검색어 가져오기(naver)
			int i=0;
			int k=1;
				for(KeyWordsVO wordvo:list)
				{		
								//검색할 단어,     검색 결과 저장할 파일명과 위치		
				BlogApi.run(wordvo.getKeyword(),"/home/sist/data/search"+i+".xml");
				//1.인기 검색어 활용해 naver검색하고 검색한 데이터 xml파일로 저장															
																				//파싱할 xml 파일 위치
				List<Item> linkList=(List<Item>) XmlParser.run("/home/sist/data/search"+i+".xml");
				//2.저장된 xml 파일 파싱해서 검색된 blog 글의 link,title 가져오기
				
				
				//3.Jsoup활용해서 해당 링크의 데이터 긁고 몽고 db에 저장
				List<BlogVO> dataList=LinkSearch.run(linkList);
					
					for(BlogVO blvo:dataList)
					{
						blvo.setKeyword(wordvo.getKeyword());
						blvo.setBlogDate(new SimpleDateFormat("yyyy.MM.dd \'at\' hh:mm:ss").format(new Date()));
						
						main.dao.blogInsert(blvo);
						System.out.println(k++);
						System.out.println(blvo.getbTitle());
						System.out.println(blvo.getbUrl());
						System.out.println(blvo.getData());
						System.out.println(blvo.getKeyword());
						System.out.println(blvo.getBlogDate());
					
				
					}
				i++;
				
				
	
			}
		}catch(Exception ex){System.out.println(ex.getMessage());}
		System.out.println("End...");
	}
}

