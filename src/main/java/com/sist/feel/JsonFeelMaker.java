package com.sist.feel;

import java.io.*;
import java.util.*;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Component;
import com.sist.mapre.Driver;

@Component
public class JsonFeelMaker {
	
	@Autowired
	private Driver dr;
	
	@Resource(name="b")
	private JobRunner jr;
	
	@Autowired
	private FeelingMatch fm;
	
	public JSONArray analFeeling (String keyword)
	{
		
		Map mapFeel=fm.getFeel(keyword);
		
		//hadoop실행
		dr.hadoopFileDelete();
		dr.copyFromLocal("analyzeFeeling.txt");
		dr.JobCall();
		dr.copyToLocal();
		
		
		JSONArray feelArry=new JSONArray();
		try{
		
		FileReader fr=new FileReader("/home/sist/thdata/result");
		BufferedReader br=new BufferedReader(fr);
		String s="";
		
		//mapreduce 결과 Json 형태로 jsp에 넘기기
		

		while((s=br.readLine())!=null)
		{
			
			String[] mRed=s.split("\t");
						
			if(mapFeel.get(mRed[0])!=null)
			{
			JSONObject feelObj=new JSONObject();
			feelObj.put("title", mRed[0]);
			feelObj.put("mer", Integer.parseInt(mRed[1])*30);
			feelObj.put("maj_atmos_comp", mapFeel.get(mRed[0]));

			feelArry.add(feelObj);
			}
		}
				
		}catch(Exception ex)
		{}
		
		return feelArry;
		
	}
	
}

