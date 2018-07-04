package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.crawling.ZumKeyWordCRW;

@Controller
public class LiveSearchController {
	@RequestMapping("main/live_search.do")
	public String livesearch_main(Model model) {
		model.addAttribute("zumKeyWord", ZumKeyWordCRW.getKeyWord());

		return "livesearch/liveSearch_main";
	}
}
