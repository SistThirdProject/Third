package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class NewsStatsController {
	   @RequestMapping("main/news_stats.do")
	    public String newsStats_main()
	    {
	   	 return "newsstats/newsStats_main";
	    }
}
