package com.sist.tiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.crawling.ZumKeyWordCRW;
import com.sist.data.DaumManager;
import com.sist.data.KeyWordsVO;
import com.sist.data.NaverManager;

@Controller
public class LiveSearchController {
	@RequestMapping("main/live_search.do")
	public String livesearch_main(Model model) {
		
		
		KeyWordsVO vo;
    	List<KeyWordsVO> list=new ArrayList<KeyWordsVO>();   
    	for(int i=0;i<20;i++)
    	{
    		vo=new KeyWordsVO();
    		vo.setKeyword("test"+i);
    		list.add(vo);
    	}
    	 	
    	
       model.addAttribute("list",list);	
       model.addAttribute("daumKeyWord", DaumManager.daumData());
       model.addAttribute("naverKeyWord", NaverManager.naverData());
		model.addAttribute("zumKeyWord", ZumKeyWordCRW.getKeyWord());

		return "livesearch/liveSearch_main";
	}
}
