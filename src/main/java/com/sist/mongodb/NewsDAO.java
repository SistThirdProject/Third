package com.sist.mongodb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;

@Repository
public class NewsDAO {
	@Autowired
	private MongoTemplate mt;

	public String getNewsData(long time, String keyword){
		String result="";
		BasicQuery query = new BasicQuery("{time:" + time + ",keyword:\"" + keyword + "\"}");
		List<NewsVO> list = mt.find(query, NewsVO.class, "news");// 500개
		for(NewsVO vo : list)
			result+=vo.getNewsdata();
		
		return result;
	}
	public KeyWordSet getKeyWordSet(long time) {
		BasicQuery query = new BasicQuery("{time:" + time + "}");
		List<NewsVO> list = mt.find(query, NewsVO.class, "news");// 500개
		HashMap<Integer, NewsVO> map = new HashMap<Integer, NewsVO>();
		for (NewsVO vo : list) {
			if (!map.containsKey(vo.getKeyword()))
				map.put(vo.getRank(), vo);
		}
		KeyWordSet set = new KeyWordSet();
		for (int i = 1; i <= map.size(); i++) {
			KeyWordVO vo = new KeyWordVO();
			NewsVO news = map.get(i);
			if (news == null)
				continue;
			vo.setKeyword(news.getKeyword());
			vo.setRank(news.getRank());
			vo.setTime(news.getTime());
			set.getKeywords()[i - 1] = vo;
			set.setTime(new Date(vo.getTime()).getHours());
		}
		return set;
	}

	public List<Long> getRecentHour() {
		List<Long> list = mt.getCollection("news").distinct("time");
		/*
		 * for(int i=0;i<list.size();i++){ BasicQuery query=new
		 * BasicQuery("{time:"+list.get(i)+"}"); if(mt.find(query,
		 * NewsVO.class,"news").size()!=500){ System.out.println(mt.find(query,
		 * NewsVO.class,"news").size()); list.remove(i); } }
		 */
		List<Long> data = new ArrayList<Long>();
		Collections.sort(list);
		Collections.reverse(list);

		data.add(list.get(0)); // 가장 최근 시간 저장
		// System.out.println(new Date(list.get(0)));
		for (int i = 0; i < list.size(); i++) {
			if (data.size() >= 24)
				break;
			// System.out.println(new Date(list.get(i)));
			if (data.get(data.size() - 1) > list.get(i) + (1000 * 60 * 50)) {
				data.add(list.get(i));
				// System.out.println("저장 "+new Date(list.get(i)));
			}
		}
		Collections.sort(data);
		return data;
	}

	public String getTimeList() {
		List<Long> list = mt.getCollection("news").distinct("time");
		JSONArray array = new JSONArray(list);
		return array.toString();
	}

	public List<NewsVO> getNewsList(long time, String keyword) {
		BasicQuery query = new BasicQuery("{time:" + time + ",keyword:\"" + keyword + "\"}");
		List<NewsVO> list = mt.find(query, NewsVO.class, "news");// 500개
		return list;
	}

	public String getKeyword(long time, int rank) {
		BasicQuery query = new BasicQuery("{time:" + time + ",rank:" + rank + "}");
		List<NewsVO> list = mt.find(query, NewsVO.class, "news");// 500개
		if (list.size() != 0)
			return list.get(0).getKeyword();
		else
			return "";
	}

	public List<NewsVO> getKeywordList(long time) {
		BasicQuery query = new BasicQuery("{time:" + time + "}");
		List<NewsVO> list = mt.find(query, NewsVO.class, "news");
		HashMap<String, NewsVO> map = new HashMap<String, NewsVO>();
		for (NewsVO vo : list) {
			if (!map.containsKey(vo.getKeyword()))
				map.put(vo.getKeyword(), vo);
		}
		ArrayList<NewsVO> result = new ArrayList<NewsVO>();
		for (NewsVO vo : map.values())
			result.add(vo);
		return result;
	}

	public List<TimeVO> getDateList() {
		List<Long> list = mt.getCollection("news").distinct("time");
		List<TimeVO> result = new ArrayList<TimeVO>();
		Collections.sort(list);
		for (Long time : list) {
			Date d = new Date(time);
			if (result.size() == 0 || !result.get(result.size() - 1).getStr().equals(d.getDate() + "일")) {
				TimeVO vo = new TimeVO();
				vo.setTime(time);
				vo.setStr(d.getDate() + "일");
				result.add(vo);
			}
		}
		return result;
	}

	public List<TimeVO> getHourList(long time) {
		int date = new Date(time).getDate();
		List<Long> list = mt.getCollection("news").distinct("time");
		List<TimeVO> result = new ArrayList<TimeVO>();
		Collections.sort(list);
		for (Long t : list) {
			Date d = new Date(t);
			if (d.getDate() != date)
				continue;
			if (result.size() == 0 || !result.get(result.size() - 1).getStr().equals(d.getHours() + "시")) {
				TimeVO vo = new TimeVO();
				vo.setTime(t);
				vo.setStr(d.getHours() + "시");
				result.add(vo);
			}
		}
		return result;
	}

	public List<TimeVO> getMinuteList(long time) {
		int date = new Date(time).getDate();
		int hour = new Date(time).getHours();
		List<Long> list = mt.getCollection("news").distinct("time");
		List<TimeVO> result = new ArrayList<TimeVO>();
		Collections.sort(list);
		for (Long t : list) {
			Date d = new Date(t);
			if (d.getDate() != date)
				continue;
			if (d.getHours()!= hour)
				continue;
			if (result.size() == 0 || !result.get(result.size() - 1).getStr().equals(d.getMinutes() + "분")) {
				TimeVO vo = new TimeVO();
				vo.setTime(t);
				vo.setStr(d.getMinutes() + "분");
				result.add(vo);
			}
		}
		return result;
	}

	public Collection<NewsVO> getKeyWordList(int date, int hour, int minute) {
		long t = new Date(2018, 7, date, hour, minute).getTime();

		/*
		 * t-60000< t < t+60000
		 */

		List<NewsVO> list = new ArrayList<NewsVO>();
		HashMap<String, NewsVO> map = new HashMap<String, NewsVO>();
		for (NewsVO vo : list) {
			if (!map.containsKey(vo.getKeyword())) {
				map.put(vo.getKeyword(), vo);
			}
		}
		return map.values();
	}

}