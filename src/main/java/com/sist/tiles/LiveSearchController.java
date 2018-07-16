package com.sist.tiles;

import java.util.*;
import java.util.Map.Entry;

import org.apache.hadoop.hive.ql.parse.HiveParser.sortByClause_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.graphite.Graphite;
import com.sist.R.RManager;
import com.sist.crawling.ZumKeyWordCRW;

import com.sist.data.KeyWordsVO;
import com.sist.data.NaverManager;
import com.sist.mongodb.NewsDAO;
import com.sist.vo.GraphItem;
import com.sist.vo.KeyWordSet;
import com.sist.vo.KeyWordVO;

@Controller
public class LiveSearchController {
	@Autowired
	private NewsDAO dao;
	@Autowired
	private RManager r;
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

		List<Long> timelist = dao.getRecentHour();
		List<KeyWordSet> setList = new ArrayList<KeyWordSet>();
		for (Long time : timelist)
			setList.add(dao.getKeyWordSet(time));
			
		r.graphDraw(setList);
		
		model.addAttribute("setList",setList);

		return "livesearch/liveSearch_main";
	}
}
class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V> 
        sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}