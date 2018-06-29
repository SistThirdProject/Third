package com.sist.blog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LinkReader {

	public static List<String> run(String fileName)
	{
		List<String> linkList=new ArrayList<String>();
		try{
		
		File file=new File("/home/sist/blog/"+fileName);
		FileReader fileReader=new FileReader(file);
		BufferedReader lineReader=new BufferedReader(fileReader);
		String line="";
		//읽을 라인이(text가) 없을때까지
		while((line=lineReader.readLine())!=null)
		{
			//System.out.println(line);
			linkList.add(line);
		}
		lineReader.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return linkList;
	}
	
}
