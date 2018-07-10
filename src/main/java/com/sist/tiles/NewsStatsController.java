package com.sist.tiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class NewsStatsController {
	   @RequestMapping("main/news_stats.do")
	    public String newsStats_main(Model model)
	    {
		   JSONArray arry=new JSONArray();
		   
		   try{
		   FileReader fr=new FileReader("/home/sist/news/result");
		   BufferedReader br=new BufferedReader(fr);
		   
		   String s="";
		   String[] js;
		   while((s=br.readLine())!=null){
			   System.out.println(s);
			   
			   js=s.split("\t");
			   System.out.println(js[0]);
			   System.out.println(js[1]);
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
}
