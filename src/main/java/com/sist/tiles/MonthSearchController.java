package com.sist.tiles;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.mongodb.NewsDAO;

@Controller
public class MonthSearchController {
	@Autowired
	private NewsDAO dao;

	@RequestMapping("main/month_search.do")
	public String monthsearch_main(String time, String rank, Model model) {	
		if (rank == null)
			rank = "1";
		String keyword=dao.getKeyword(Long.parseLong(time), Integer.parseInt(rank));
		SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 hh시 mm분");
		model.addAttribute("title", sdf.format(new Date(Long.parseLong(time))) + " " + keyword);
		Collection<Integer> datelist = dao.getDateList();
		model.addAttribute("newslist", dao.getNewsList(Long.parseLong(time), keyword));
		model.addAttribute("datelist", datelist);
		model.addAttribute("timelist", dao.getTimeList());
		model.addAttribute("keywordlist", dao.getKeywordList(Long.parseLong(time)));
		return "monthsearch/monthSearch_main";
	}
}
