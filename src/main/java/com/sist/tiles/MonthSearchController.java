package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MonthSearchController {
	
	    @RequestMapping("main/month_search.do")
	    public String monthsearch_main()
	    {
	   	 return "monthsearch/monthSearch_main";
	    }
}
