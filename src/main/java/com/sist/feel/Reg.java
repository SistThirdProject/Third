package com.sist.feel;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reg {

	public void analyzeFeeling(String data) {
		// TODO Auto-generated method stub

		try{
		FileReader fr=new FileReader("c://feeling.csv");
		File f=new File("c://feeling.csv");
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis,"EUC-KR");
		BufferedReader br=new BufferedReader(isr);
		String s="";
		
		List<String> list=new ArrayList<String>();
		List<String> getFeel=new ArrayList<String>();
			
			
			
			//data.trim();
			//data 변수에 감정사전에서 가져온 데이터를 넣을 예정
			String[] feel=data.split("\n");
			List<String> feelStatic=new ArrayList<String>();
			//Map<String, String> feelM=new HashMap<String, String>();
			for(int i=1;i<feel.length;i++)
			{
				StringTokenizer stz=new StringTokenizer(feel[i], ",");
				/*String ss[]=feel[i].split(" ");
				System.out.println(ss[1]+" "+ss[2]);*/
				//feelM.put(ss[1], ss[2]);
				for(int j=0;j<stz.countTokens();j++)
				{
					
					String fe=stz.nextToken();
					//System.out.println(fe);
					
						if(j==1)
						{
							
								//감정단어 ex - 눈물난다.
								list.add(fe);
								//getFeel.add(fe.substring(0, 3));
								
							
						}
						else if(j==2)
						{
							//감정 카테고리 ex- 눈물난다 =>슬픔
							feelStatic.add(fe);
							getFeel.add(fe);
						}
					
					
				}
				
			}
			
			/*List<String> last=new ArrayList<String>();
			
			for(int i=0;i<feelStatic.size();i++)
			{
				String s1=feelStatic.get(i);
				
				if(!last.contains(feelStatic.get(i)))
						{
							last.add(s1);
						};
				
			}*/
			
			
			
			FileReader fr1=new FileReader("c://mongo.txt");
			BufferedReader br2=new BufferedReader(fr1);
			String contentData="";
			while((s=br2.readLine())!=null)
				contentData+=s;
			//contentData변수에 몽고디비에서 가져올 데이터를 넣을 예정
			
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).length()>2)
				{	//content 감정사전에 있는 단어가 얼마나 있는지 mapping
					Pattern pattern=Pattern.compile(list.get(i).substring(0,3));
					Matcher match=pattern.matcher(data);
					while(match.find()){
						//System.out.println(match.group());
						for(int j=0;j<getFeel.size();j++)
						{
							if(getFeel.get(j).length()>2)
							{
								if(getFeel.get(j).substring(0,3).equals(match.group()))
								{
									System.out.println(getFeel.get(j+1));
								}
							}
						}
						//System.out.println(feelM.get(match.group()));
					}
				}
			}
			
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	

}