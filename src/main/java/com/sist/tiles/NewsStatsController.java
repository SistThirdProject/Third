package com.sist.tiles;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.R.NewsRManager;
import com.sist.mapre.Driver;
import com.sist.naverApi.Rap;
import com.sist.news.KHCRW;
@Controller
public class NewsStatsController {
	
	@Autowired
	private Driver newsWordc;
	
	@Autowired
	private Rap rap;
	
	@Autowired
	private NewsRManager nrm;
	
	   @RequestMapping("main/news_main.do")
	    public String newsStats_main(String year, Model model)
	    {
		   
		   if(year==null)
			   year="2007";
		   newsWordc.hadoopFileDelete();
		   newsWordc.copyFromLocal(year+".CSV");
		   newsWordc.JobCall();
		   newsWordc.copyToLocal();
		   
		   JSONArray arry=new JSONArray();
		   System.out.println(year);
		   try{
		   FileReader fr=new FileReader("/home/sist/news/result");
		   BufferedReader br=new BufferedReader(fr);
		   
		   String s="";
		   String[] js;
		   while((s=br.readLine())!=null){
			   System.out.println(s);
			   
			   js=s.split("\t");
				  if(js[0].length()>1)
				  {
				   JSONObject obj=new JSONObject();
				   obj.put("text", js[0]);
				   obj.put("weight", js[1]);
				   arry.add(obj);
				   //System.out.println(s);
				  }
			  }
		   
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   model.addAttribute("arry",arry);
	   	 return "news/news_main";
	    }
	   
	   @RequestMapping("main/newsRatio.do")
	   public String newsRatio(String year,String keyword, Model model)
	   {
		   List<JSONArray> list=new ArrayList<JSONArray>();
		   String totalData="";
		   JSONObject data=new JSONObject();
		   try{
				
				String result=rap.graph(keyword,year);
				JSONParser parser=new JSONParser();
				JSONObject obj=(JSONObject) parser.parse(result);
				JSONArray arry=(JSONArray)obj.get("results");
				
				
				for(int i=0;i<arry.size();i++)
				{
					JSONObject dataObj=(JSONObject)arry.get(i);
					System.out.println(dataObj);
					JSONArray keyArr=(JSONArray)dataObj.get("keywords");
						for(int j=0;j<keyArr.size();j++)
						{
							keyword=(String) keyArr.get(i);
							System.out.println(keyword);
						}
						
					JSONArray ratio=(JSONArray)dataObj.get("data");
					
					for(int j=0;j<ratio.size();j++)
					{
						JSONObject dataInObj=(JSONObject) ratio.get(j);
						//System.out.println(ratio.get(j));
						JSONArray json=new JSONArray();
						json.add(dataInObj.get("period"));
						json.add(dataInObj.get("ratio"));
						json.add("color: #e5e4e2");
						data.put("data", json);
						list.add(json);
						if(dataInObj.get("ratio")=="100")
						{
							String[] s=dataInObj.get("period").toString().split("-");
							//List<String> list3=KHCRW.get(keyword, Integer.parseInt(s[0]), Integer.parseInt(s[1]));
							totalData=KHCRW.get(keyword, Integer.parseInt(s[0]), Integer.parseInt(s[1]));
							
						}
						System.out.println(dataInObj.get("period"));
						System.out.println(dataInObj.get("ratio"));
					}

				}
				}
				catch(Exception ex)
				{
					
				}
		   nrm.rWordCloud(totalData);
		   
		   
		   model.addAttribute("keyword",keyword);
		   model.addAttribute("data",data);
		   model.addAttribute("list",list);
		   return "newsRatio";
	   }
}
