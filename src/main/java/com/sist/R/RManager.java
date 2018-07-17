package com.sist.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

import com.sist.vo.GraphItem;
import com.sist.vo.KeyWordSet;
import com.sist.vo.KeyWordVO;

@Component
public class RManager {

	public void getKeyWordC(String file)
	{
		try{
			RConnection rc=new RConnection();
			
			rc.voidEval("library(KoNLP)");
			rc.voidEval("library(wordcloud2)");
			rc.voidEval("library(webshot)");
			rc.voidEval("library(htmlwidgets)");
			rc.voidEval("data<-readLines(\""+file+"\")");
			rc.voidEval("data2<-sapply(data, extractNoun,USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data4<-table(data3)");
			rc.voidEval("data5<-head(sort(data4,decreasing = T),100)");
			rc.voidEval("my_graph=wordcloud2(data5, size = 0.7, shape = \'star\')");
			rc.voidEval("saveWidget(my_graph,\"/home/sist/springDev3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ThirdProject/star.html\",selfcontained = F)");
			
			rc.voidEval("dev.off()");
			rc.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void graphDraw(List<KeyWordSet> setList) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (KeyWordSet set : setList) {
			for (KeyWordVO key : set.getKeywords()) {
				if(key==null)
					continue;
				String keyword = key.getKeyword();
				
				if (!map.containsKey(keyword))
					map.put(keyword, 1);
				else
					map.put(keyword, map.get(keyword) + 1);
			}
		}
		List keylist = new ArrayList(MapUtil.sortByValue(map).entrySet());
		Collections.reverse(keylist);

		List<GraphItem> glist = new ArrayList<GraphItem>();
		for (KeyWordSet set : setList) {
			for (KeyWordVO key : set.getKeywords()) {
				if(key==null)
					continue;
				String keyword = key.getKeyword();
				for (int i = 0; i < 10; i++) {
					if (((Entry<String, Integer>) keylist.get(i)).getKey().equals(keyword)) {
						GraphItem item = new GraphItem();
						item.setKeyword(keyword);
						item.setRank(key.getRank());
						item.setTime(set.getTime());
						glist.add(item);
					}
				}
			}
		}
		String time = "\"" + String.valueOf(glist.get(0).getTime()) + "\"";
		String keyword = "\"" + String.valueOf(glist.get(0).getKeyword()) + "\"";
		String rank = "\"" + String.valueOf(glist.get(0).getRank()) + "\"";

		for (int i = 1; i < glist.size(); i++) {
			time += ",\"" + String.valueOf(glist.get(i).getTime()) + "\"";
			keyword += ",\"" + String.valueOf(glist.get(i).getKeyword()) + "\"";
			rank += ",\"" + String.valueOf(glist.get(i).getRank()) + "\"";
		}
		String realPath = "/home/sist/new/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ThirdProject/";
		String[] query = { "table<-data.frame(time=c(" + time + "),keyword=c(" + keyword + "),rank=c(" + rank + "))",
				"library(dplyr)", "library(plotly)", "library(ggplot2)",
				"table$rank=table$rank %>% factor(levels=c(10:1))",
				"nowhour=format(Sys.time(), \"%H\") %>% as.numeric()", "nowlevels=c((nowhour-23):nowhour) %%24",
				"table$time=table$time %>% factor(levels=nowlevels)",
				"graph=ggplot(data = table, aes(x = time, y = rank, group = keyword, colour = keyword)) + geom_line()+ geom_point(size=2)",
				"html=ggplotly(graph)", "htmltools::save_html(html,\""+realPath+"graph.html\")", "while (!is.null(dev.list()))  dev.off()"

		};
		try {
			RConnection rc = new RConnection();
			for (String str : query)
				rc.voidEval(str);
			rc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("R exception");
		}
	}
	
	
}
	
	
class MapUtil {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}