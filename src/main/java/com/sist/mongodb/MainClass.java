package com.sist.mongodb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;

import com.sist.blog.BlogApi;
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
			
			BlogVO blvo=new BlogVO();
			String blogData="";
			List<KeyWordsVO> list=NaverManager.naverData();
			
			for(KeyWordsVO wordvo:list)
			{					//검색할 단어,     검색 결과 저장할 파일명과 위치		
			BlogApi.run(wordvo.getKeyword(),"/home/sist/data/search.xml");
																		
																			//파싱할 xml 파일 위치
			List<String> linkList=(List<String>) XmlParser.run("/home/sist/data/search.xml");
			for(String link:linkList)
			{
				blogData=LinkSearch.run(linkList);
			}
				blvo.setKeyword(wordvo.getKeyword());
				blvo.setData(blogData);
				main.dao.blogInsert(blvo);
			}
			

			System.out.println("End...");
			

		}catch(Exception ex){}
	}
}
