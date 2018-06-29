package com.sist.blog;

import java.util.*;

public class CrawlingController {

	public static void main(String[] args)
	{
			//1.Naver Open API 활용해서 검색할 키워드 입력 후 결과값 xml로 저장
			//BlogApi.run("축구");
				
			//2. xml 파싱해서 link만 모아 txt파일로 저장
			String linkFile=XmlParser.run("linkfile");
			
			//3. link.txt 파일 읽어서 list로 Jsoup 또는 selenium에 전달
			List<String> linkList=LinkReader.run(linkFile);
			
			for(String link:linkList)
			{
				LinkSearch.run(link);
			}
			
			
			System.out.println("End...");
	}
	
}
