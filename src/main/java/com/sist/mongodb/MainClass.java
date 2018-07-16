package com.sist.mongodb;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;


import com.sist.data.KeyWordsVO;
import com.sist.data.NaverManager;
import com.sist.vo.NewsVO;



@Component
public class MainClass {
	
	@Autowired
	private BlogDAO dao;
	
	
	public static void main(String[] args)
	{
		try{
			
			ApplicationContext app=new ClassPathXmlApplicationContext("application-mongo.xml");
			
			MainClass main=(MainClass)app.getBean("mainClass");
			
			String keyword="홍수현";
			//mongo에서 키워드에 해당하는 데이터 가져오기
			List<BlogVO> list=main.dao.getBlogData(keyword);
			//List<NewsVO> list=main.dao.getNewsData(keyword);
			
			//가져온 데이터 txt파일로 생성
			FileWriter f=new FileWriter("/home/sist/data/mongo.txt");
			
			for(BlogVO vo:list)
			{
				f.write(vo.getNewsdata()+"\n");
				//System.out.println(vo.getData());
			}
			
			f.close();
			
			//3. 감정 분석 사전 읽어오기
			String s="";
			String data="";
			FileReader fr=new FileReader("/home/sist/다운로드/feeling.csv");
			/*File f=new File("c://feeling.csv");
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis,"EUC-KR");*/
			BufferedReader br=new BufferedReader(fr);
			while((s=br.readLine())!=null)
			{
				data+=s+"\n";
				//System.out.println(s);
			}
			
			List<String> feelList=new ArrayList<String>();
			List<String> feelCate=new ArrayList<String>();

   				//data 변수에 감정사전에서 가져온 데이터를 넣을 예정
				String[] feel=data.split("\n");
				List<String> feelStatic=new ArrayList<String>();
				
				for(int i=1;i<feel.length;i++)
				{
					StringTokenizer stz=new StringTokenizer(feel[i], ",");
					for(int j=0;j<stz.countTokens();j++)
					{
						
						String fe=stz.nextToken();
						//System.out.println(fe);
						
							if(j==1)
							{
								
								//감정단어 ex - 눈물난다.
								feelList.add(fe);
									
							}
							else if(j==2)
							{
								//감정 카테고리 ex- 눈물난다 =>슬픔
								feelStatic.add(fe);
								feelCate.add(fe);
							}
						
					}
					
				}
			//감정,감정카테 map에 담기
			Map feelMatch=new HashMap();
			for(int i=0;i<feelList.size();i++)
			{
				if(feelList.get(i).length()>2)
				{
					feelMatch.put(feelList.get(i).substring(0,3), feelStatic.get(i));
					//System.out.println(feelList.get(i));
					//System.out.println(feelStatic.get(i));
				}
			}

			//2개씩 자른 감정 담기
			List<String> feelData=new ArrayList<String>();
			
			for(String psf:feelList)
			{
				if(psf.length()>2)
				feelData.add(psf.substring(0,3));
			}
			
			
			//감정 분석할 txt 가져오기
			FileReader frMongo=new FileReader("/home/sist/data/mongo.txt");
			BufferedReader brMongo=new BufferedReader(frMongo);
			String data2="";
			while((s=brMongo.readLine())!=null)
				data2+=s;
			
			
			for(int i=0;i<feelData.size();i++)
			{
					//content 감정사전에 있는 단어가 얼마나 있는지 mapping
					Pattern pattern=Pattern.compile(feelData.get(i));
					Matcher match=pattern.matcher(data2);
					while(match.find())
					{
						System.out.print(match.group()+"-");
						System.out.println(feelMatch.get(match.group()));
						
					}
				
			}
			
			/*Pattern[] p=new Pattern[feelData.size()];
			
			for(int i=0;i<p.length;i++)
			{
				p[i]=Pattern.compile(feelData.get(i));
				for(int j=0;j<list.size();j++)
				{
				Matcher match=p[i].matcher(list.get(j).toString());
					while(match.find())
					{
						System.out.println(match.group());
					}
				}
			}*/
			
			/*List<KeyWordsVO> list=NaverManager.naverData();
			//인기검색어 가져오기(naver)
			
			int k=1;
			
			File f=new File("/home/sist/data/search.xml");
			
				for(KeyWordsVO wordvo:list)
				{		
				
								//검색할 단어,     검색 결과 저장할 파일명과 위치		
				BlogApi.run(wordvo.getKeyword(),"/home/sist/data/search.xml");
				//1.인기 검색어 활용해 naver검색하고 검색한 데이터 xml파일로 저장			
				
														  //파싱할 xml 파일 위치
				List<Item> linkList = XmlParser.run("/home/sist/data/search.xml");
				//2.저장된 xml 파일 파싱해서 검색된 blog 글의 link,title 가져오기
				
				
				//3.Jsoup활용해서 해당 링크의 데이터 긁고 몽고 db에 저장
				List<BlogVO> dataList=LinkSearch.run(linkList);
					
					for(BlogVO blvo:dataList)
					{
						blvo.setKeyword(wordvo.getKeyword());
						
						
						main.dao.blogInsert(blvo);
						System.out.println(k++);
						System.out.println(blvo.getTitle());
						System.out.println(blvo.getUrl());
						System.out.println(blvo.getData());
						System.out.println(blvo.getKeyword());
						System.out.println(blvo.getTime());
					
				
					}
				
					//f.delete();
	
			}*/
		}catch(Exception ex){System.out.println(ex.getMessage());}
		System.out.println("End...");
	}
}

