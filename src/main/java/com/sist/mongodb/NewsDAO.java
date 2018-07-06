package com.sist.mongodb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;

@Repository
public class NewsDAO {
	@Autowired
	private MongoTemplate mt;

	public KeyWordSet getKeyWordSet(long time){
		BasicQuery query=new BasicQuery("{time:"+time+"}");
		List<NewsVO> list = mt.find(query, NewsVO.class,"news");//500개
		HashMap<Integer, NewsVO> map = new HashMap<Integer, NewsVO>(); 
		for(NewsVO vo : list){
			if(!map.containsKey(vo.getKeyWord()))
				map.put(vo.getRank(), vo);
		}
		KeyWordSet set = new KeyWordSet();
		for(int i=1;i<=10;i++){
			KeyWordVO vo = new KeyWordVO();
			NewsVO news = map.get(i);
			vo.setKeyword(news.getKeyWord());
			vo.setRank(news.getRank());
			vo.setTime(news.getTime());
			set.getKeywords()[i-1]=vo;
			set.setTime(new Date(vo.getTime()).getHours());
		}				
		return set;
	}

	public List<Long> getRecentHour() {
		List<Long> list = mt.getCollection("news").distinct("time");
		List<Long> data = new ArrayList<Long>();
		Collections.sort(list);
		Collections.reverse(list);

		data.add(list.get(0)); // 가장 최근 시간 저장
		for (int i = 0; i < list.size(); i++) {
			if (data.size() > 24)
				break;
			if (data.get(data.size() - 1) > list.get(i) + (1000 * 60 *5))
				data.add(list.get(i));
		}
		Collections.sort(data);
		return data;
	}

	public Collection<Integer> getDateList() {
		List<Long> list = mt.getCollection("news").distinct("time");
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();
		for (long time : list) {
			Date d = new Date(time);
			if (!map.containsKey(d.getDate())) {
				map.put(d.getDate(), time);
			}
		}
		return map.keySet();
	}

	public Collection<Integer> getHourList(int date) {
		List<Long> list = mt.getCollection("news").distinct("time");
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();
		for (long time : list) {
			Date d = new Date(time);
			if (d.getDate() == date && !map.containsKey(d.getHours())) {
				map.put(d.getHours(), time);
			}
		}
		return map.keySet();
	}

	public Collection<Integer> getMinuteList(int date, int hour) {
		List<Long> list = mt.getCollection("news").distinct("time");
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();
		for (long time : list) {
			Date d = new Date(time);
			if (d.getDate() == date && d.getHours() == hour && !map.containsKey(d.getMinutes())) {
				map.put(d.getMinutes(), time);
			}
		}
		return map.keySet();
	}

	public Collection<NewsVO> getKeyWordList(int date, int hour, int minute) {
		long t = new Date(2018, 7, date, hour, minute).getTime();

		/*
		 * t-60000< t < t+60000
		 */

		List<NewsVO> list = new ArrayList<NewsVO>();
		HashMap<String, NewsVO> map = new HashMap<String, NewsVO>();
		for (NewsVO vo : list) {
			if (!map.containsKey(vo.getKeyWord())) {
				map.put(vo.getKeyWord(), vo);
			}
		}
		return map.values();
	}

}
