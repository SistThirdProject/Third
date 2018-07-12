package com.sist.graph;

import com.sist.json.DataOne;
import com.sist.json.Result;
import com.sist.json.UserVO;

import scala.Array;

import com.sist.json.Trand;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;

public class Main {

	public static void main(String[] args) throws ParseException
	{
		Main main=new Main();
		List<JSONArray> list1=main.data();
		int i=0;
		for(JSONArray j:list1){
			System.out.println(j.get(0));
			System.out.println(j.get(1));
		}
		
	}
	public List<JSONArray> data() throws ParseException {
		NaverRat getRetio = new NaverRat();
		String json;
		List<JSONArray> list=new ArrayList<JSONArray>();
		
		// 사용자 입력
		UserVO vo=new UserVO();
		vo.setSdate("2018-01-01"); // 시작 날짜
		vo.setEdate("2018-07-01"); // 끝나는 날짜
		vo.setGname("월드컵"); // 그래프에 표시되는 주제어
		vo.setKeyword("박지성"); // 검색할 키워드
		
		json = getRetio.rat(vo);
		//System.out.println(json);
		
		JSONParser p = new JSONParser();
		
		JSONObject obj= (JSONObject) p.parse(json);
		//System.out.println(obj);
		JSONArray array=(JSONArray) obj.get("results");
		String key="";
		for(int i=0;i<array.size();i++)
		{
			JSONObject obj1=(JSONObject) array.get(i);
			//key=(String) obj1.get("keywords");
			//System.out.println(obj1.get("keywords"));
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
				System.out.println(obj2.get("period"));
				System.out.println(obj2.get("ratio"));
				
			}
			//System.out.println(obj1.get("data"));
			
		}
		//System.out.println("----------------------------------");
		
		//간단하게 수정해보기
		//JsonArray jArray = (JsonArray) (((JsonObject) p.parse(json)).get("results"));
		
		//기간과 비율 출력
		/*for (int i = 0; i < jArray.size(); i++) {
			JsonObject jObj = (JsonObject) jArray.get(i);
			
			// 타이틀 출력
			System.out.println("title : " + jObj.get("title"));

			// 키워드들을 담기위한 json배열 생성
			JSONArray keywords =  jObj.get("keywords");
			
			String key="";
		//	System.out.print("keyword : ");
			for (int j = 0; j < keywords.size(); j++) {
			key=keywords.get(j);
				System.out.print(keywords.get(j) + " ");
			}
			System.out.println();
			
			
			
			// 년도와 키우더 담기 위한 Json 배열 생성
			JSONArray arr1=new JSONArray();
			arr1.add("년도");
			arr1.add("");
			// 기간과 비율을 담기 위한 json배열 생성
			JsonArray datas = (JsonArray) jObj.get("data");
			
			// datas를 JosnObject에 담고 출력한다.
			for (int j = 0; j < datas.size(); j++) {
				JsonObject data = (JsonObject) datas.get(j);
				
				String period = data.get("period").toString();
				String ratio = data.get("ratio").toString();
				// 출력
				System.out.println("period -- " + period + " / " + ratio);
				
			}
			
			//System.out.println("\n****************");
			
		}*/
		/******************************************************************/
		// 자바 객체로 한번에 변환해보기
	//	System.out.println("-----여기서부터 gson-------");
		/*Gson gson = new Gson();
		Trand trand = gson.fromJson(json, Trand.class);*/
		
	//	System.out.println("시작날짜 : " + trand.getStartDate());
	//	System.out.println("끝날짜 : " + trand.getEndDate());
	//	System.out.println("- - - - - - - - - - - - - -");
		/*for (Result result : trand.getResults()) {
			System.out.println("주제어 : " + result.getTitle());
			
			System.out.print("검색어 : ");
			for (String keyword : result.getKeywords()) {
				System.out.print(keyword + " ");
			}
			
			System.out.println("\n날짜와 비율");
			for (DataOne one : result.getData()) {
				String period = one.getPeriod();
				String ratio = one.getRatio();
				
				System.out.println(period + " / " + ratio);
			}
			// System.out.println("\n---");
		}*/
		return list;
	}
	
}