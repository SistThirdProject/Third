package com.sist.tiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.crawling.ZumKeyWordCRW;
import com.sist.data.DaumManager;
import com.sist.data.KeyWordsVO;
import com.sist.data.NaverManager;
import com.sist.mongodb.NewsDAO;
import com.sist.vo.KeyWordSet;

@Controller
public class LiveSearchController {
	@Autowired
	private NewsDAO dao;
	@RequestMapping("main/live_search.do")
	public String livesearch_main(Model model) {

		KeyWordsVO vo;
		List<KeyWordsVO> list = new ArrayList<KeyWordsVO>();
		for (int i = 0; i < 20; i++) {
			vo = new KeyWordsVO();
			vo.setKeyword("test" + i);
			list.add(vo);
		}

		model.addAttribute("list", list);
		model.addAttribute("daumKeyWord", DaumManager.daumData());
		model.addAttribute("naverKeyWord", NaverManager.naverData());
		model.addAttribute("zumKeyWord", ZumKeyWordCRW.getKeyWord());

		List<Long> timelist = dao.getRecentHour();
		List<KeyWordSet> setList = new ArrayList<KeyWordSet>();
		for(Long time: timelist){
			setList.add(dao.getKeyWordSet(time));
		}
		model.addAttribute("setList",setList);
		
		return "livesearch/liveSearch_main";
	}
}
