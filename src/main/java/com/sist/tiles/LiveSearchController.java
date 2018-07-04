package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LiveSearchController {
    @RequestMapping("main/live_search.do")
    public String livesearch_main()
    {
    	
    	
    	
   	 return "livesearch/liveSearch_main";
    }
}
