package com.sist.feel;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.mongodb.BlogDAO;
import com.sist.vo.BlogVO;

@Component
public class FeelingRegex {

	@Autowired
	private BlogDAO dao;
	
	public Map getFeel(String keyword)
	{
		Map feelMatch=new HashMap();
		List<String> mapList=new ArrayList<String>();
		try{
			
			//mongo에서 키워드에 해당하는 데이터 가져오기
			List<BlogVO> list=dao.getBlogData(keyword);
			//List<NewsVO> list=main.dao.getNewsData(keyword);
			
			//가져온 데이터 blogData에 담아두기
			String blogData="";
			
			for(BlogVO vo:list)
			{
				blogData+=vo.getNewsdata()+"\n";
			}
			System.out.println(blogData);
			//2. 감정 분석 사전 읽어오기
			String s="";
			String data="";
			FileReader fr=new FileReader("/home/sist/thdata/feeling.csv");
			BufferedReader br=new BufferedReader(fr);
			
			while((s=br.readLine())!=null)
			{
				//data 변수에 감정사전에서 가져온 데이터 insert
				data+=s+"\n";
			}
			
			List<String> feelList=new ArrayList<String>();
			List<String> feelCate=new ArrayList<String>();
			List<String> totalFeel=new ArrayList<String>();
				
				String[] feel=data.split("\n");
				for(int i=1;i<feel.length;i++)
				{
					//가져온 csv파일에서 ,를 구분자로 데이터를 나눔
					StringTokenizer stz=new StringTokenizer(feel[i], ",");
					
					for(int j=0;j<stz.countTokens();j++)
					{
						
						String csvColumn=stz.nextToken();
						//System.out.println(fe);
						
							if(j==1)
							{
								
								//감정단어 ex - 눈물난다.
								feelList.add(csvColumn);
									
							}
							else if(j==2)
							{
								//감정 카테고리 ex- 눈물난다 =>슬픔
								feelCate.add(csvColumn);
								
								//중복되지 않는 감정 넣기
								if(!totalFeel.contains(csvColumn))
									totalFeel.add(csvColumn.substring(0,2));
							}
					}
					
				}
				
				
				//3. feelcate를 긍정,부정,중립으로 분류
				String[] feelCa={"혐오","슬픔","기쁨","중성","흥미","분노","지루","놀람","공포","기타","통증"};
				String[] feelDiv={"긍정","부정","중립"};
				
				int z=0;
				for(String s1:totalFeel)
				{
					if(s1.equals(feelCa[z]))
						feelMatch.put(s1, feelDiv[z+1]);
					else if(s1.equals(feelCa[z+1]))
						feelMatch.put(s1, feelDiv[z+1]);
					else if(s1.equals(feelCa[z+2]))
						feelMatch.put(s1, feelDiv[z]);
					else if(s1.equals(feelCa[z+3]))
						feelMatch.put(s1, feelDiv[z+2]);
					else if(s1.equals(feelCa[z+4]))
						feelMatch.put(s1, feelDiv[z]);
					else if(s1.equals(feelCa[z+5]))
						feelMatch.put(s1, feelDiv[z+1]);
					else if(s1.equals(feelCa[z+6]))
						feelMatch.put(s1, feelDiv[z+2]);
					else if(s1.equals(feelCa[z+7]))
						feelMatch.put(s1, feelDiv[z]);
					else if(s1.equals(feelCa[z+8]))
						feelMatch.put(s1, feelDiv[z+1]);
					else if(s1.equals(feelCa[z+9]))
						feelMatch.put(s1, feelDiv[z]);
					else if(s1.equals(feelCa[z+10]))
						feelMatch.put(s1, feelDiv[z+1]);
				}
				
				
			//감정(3글자 자른것),감정카테 map에 담기
			for(int i=0;i<feelList.size();i++)
			{
				if(feelList.get(i).length()>2)
				{												//감정, 감정 카테고리							
					feelMatch.put(feelList.get(i).substring(0,3), feelCate.get(i).toString().substring(0,2)+feelList.get(i));
				}
			}

			//감정분석을 위해 감정을 앞 3글자만 잘라서 list에 담기
			List<String> feelData=new ArrayList<String>();
			
			for(String psf:feelList)
			{
				if(psf.length()>2)
				feelData.add(psf.substring(0,3));
			}
			

			String feelAnalyze="";
			for(int i=0;i<feelData.size();i++)
			{
					//감정사전에 있는 단어가 blogdata 얼마나 있는지 검색
					Pattern pattern=Pattern.compile(feelData.get(i));
					
												//감정 분석할 blogData 가져오기
					Matcher match=pattern.matcher(blogData);
					while(match.find())
					{	
						String matchedFeel=match.group();
						
						if(matchedFeel!=null)
						{
						//System.out.println(matchedFeel);
						//매칭된 단어를 analyze에 넣기
						feelAnalyze+=feelMatch.get(matchedFeel).toString().substring(2)+",";
						
						feelMatch.put(feelMatch.get(matchedFeel).toString().substring(2),feelMatch.get(feelMatch.get(matchedFeel).toString().substring(0,2)));
						//mapList.add(feelMatch.get(match.group()).toString().substring(0,2));
						//System.out.println(feelMatch.get(feelMatch.get(matchedFeel)));
						}
					}
				
			}
			
			FileWriter fw=new FileWriter("/home/sist/thdata/analyzeFeeling.txt");
			fw.write(feelAnalyze);
			fw.close();
			
		}catch(Exception ex){System.out.println(ex.getMessage());}
		
		
		return feelMatch;
	}
	
}
