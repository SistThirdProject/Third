package com.sist.tiles;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.feel.JsonFeelMaker;
import com.sist.graph.Main;
import com.sist.mapre.Driver;
import com.sist.mongodb.NewsDAO;
import com.sist.vo.NewsVO;
import com.sist.vo.TimeVO;

@Controller
public class MonthSearchController {
	@Autowired
	private NewsDAO dao;
	
	@Autowired
	private Driver driver;
	
	@Autowired
	private JsonFeelMaker jfm;

	@RequestMapping("main/month_search.do")
	public String monthsearch_main(String time, String rank, Model model) {
		if (time == null)
			time = "1530865442663";
		if (rank == null)
			rank = "1";
		String keyword = dao.getKeyword(Long.parseLong(time), Integer.parseInt(rank));
		String graphdata = "";
		try {
			graphdata = new Main().data(time, keyword).toString();
		} catch (Exception e) {
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 hh시 mm분");
		model.addAttribute("title", sdf.format(new Date(Long.parseLong(time))) + " " + keyword);
		model.addAttribute("newslist", dao.getNewsList(Long.parseLong(time), keyword));
		model.addAttribute("graphdata", graphdata);
		model.addAttribute("keywordlist", dao.getKeywordList(Long.parseLong(time)));

		model.addAttribute("datelist", dao.getDateList());
		model.addAttribute("hourlist", dao.getHourList(Long.parseLong(time)));
		model.addAttribute("minutelist", dao.getMinuteList(Long.parseLong(time)));
		model.addAttribute("cloud",getData(dao.getNewsData(Long.parseLong(time), keyword)));
		return "monthsearch/monthSearch_main";
	}
	
	@RequestMapping("main/feel.do")
	   public String feel(String keyword,Model model)
	   {
	
		   JSONArray jarry=jfm.analFeeling("고혈압약 발암물질 리스트");
		  
		   model.addAttribute("jarry",jarry);
		   
		   return "feel";
	   }

	public List<String> getData(String data) {
		List<String> list = new ArrayList<String>();
		String strToExtrtKwrd = data;
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
		for (int i = 0; i < kl.size(); i++) {
			Keyword kwrd = kl.get(i);
			String str = kwrd.getString();
			str = str.replaceAll("[^가-힣]+", "");
			if (str.length() > 1 && kwrd.getCnt() >= 4) 				
				list.add(str);			
		}		
		return list;
	}
}
