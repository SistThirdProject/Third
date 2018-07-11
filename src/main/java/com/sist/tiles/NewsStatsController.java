package com.sist.tiles;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.mapre.Driver;
@Controller
public class NewsStatsController {
	
	@Autowired
	Driver newsWordc;
	
	   @RequestMapping("main/news_stats.do")
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
			  
			   JSONObject obj=new JSONObject();
			   obj.put("text", js[0]);
			   obj.put("weight", js[1]);
			   arry.add(obj);
			   //System.out.println(s);
		   }
		   
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   model.addAttribute("arry",arry);
	   	 return "newsstats/newsStats_main";
	    }
	   
	   @RequestMapping("main/newsRatio.do")
	   public String newsRatio(String year,String keyword, Model model)
	   {
		   List<JSONArray> list=new ArrayList<JSONArray>();
		   System.out.println(1234);
		   model.addAttribute(list);
		   return "newsstats/newsRatio";
	   }
}
