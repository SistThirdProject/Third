package com.sist.graph;

import com.sist.json.DataOne;
import com.sist.json.Result;
import com.sist.json.UserVO;

import scala.Array;

import com.sist.json.Trand;

import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;

public class Main {
	public List<JSONArray> data(String time,String keyword) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start =sdf.format(new Date(Long.parseLong(time)-(1000*60*60*24*7)));
		String end =sdf.format(Long.parseLong(time)); 
		NaverRat getRetio = new NaverRat();
		String json;
		List<JSONArray> list=new ArrayList<JSONArray>();
		UserVO vo=new UserVO();
		vo.setSdate(start); // 시작 날짜
		vo.setEdate(end); // 끝나는 날짜
		vo.setGname("월드컵"); // 그래프에 표시되는 주제어
		vo.setKeyword(keyword); // 검색할 키워드
		json = getRetio.rat(vo);
		JSONParser p = new JSONParser();
		JSONObject obj= (JSONObject) p.parse(json);
		JSONArray array=(JSONArray) obj.get("results");
		String key="";
		for(int i=0;i<array.size();i++)
		{
			JSONObject obj1=(JSONObject) array.get(i);
			JSONArray fa=(JSONArray) obj1.get("keywords");
			key=(String) fa.get(0);
			JSONArray arr=new JSONArray();
			arr.add("년도");
			arr.add(key);
			list.add(arr);
			JSONArray array1=(JSONArray) obj1.get("data");
			for(int j=0;j<array1.size();j++)
			{
				JSONObject obj2=(JSONObject) array1.get(j);
				JSONArray arr1=new JSONArray();
				arr1.add(obj2.get("period"));
				arr1.add(obj2.get("ratio"));
				list.add(arr1);		
			}
		}
		return list;
	}
	
}