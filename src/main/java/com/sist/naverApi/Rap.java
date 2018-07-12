package com.sist.naverApi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class Rap {

	public List<JSONArray> jso (String keyword,String year)
	{
		List<JSONArray> list=new ArrayList<JSONArray>();
		try{
		Rap ra=new Rap();
		String result=ra.graph(keyword,year);
		JSONParser parser=new JSONParser();
		JSONObject obj=(JSONObject) parser.parse(result);
		JSONArray arry=(JSONArray)obj.get("results");
		
		
		for(int i=0;i<arry.size();i++)
		{
			JSONObject dataObj=(JSONObject)arry.get(i);
			System.out.println(dataObj);
			JSONArray keyArr=(JSONArray)dataObj.get("keywords");
				for(int j=0;j<keyArr.size();j++)
				{
					keyword=(String) keyArr.get(i);
					System.out.println(keyword);
				}
				
			JSONArray ratio=(JSONArray)dataObj.get("data");
			
			for(int j=0;j<ratio.size();j++)
			{
				JSONObject dataInObj=(JSONObject) ratio.get(j);
				//System.out.println(ratio.get(j));
				JSONArray json=new JSONArray();
				json.add(dataInObj.get("period"));
				json.add(dataInObj.get("ratio"));
				json.add("color: #e5e4e2");
				list.add(json);
				System.out.println(dataInObj.get("period"));
				System.out.println(dataInObj.get("ratio"));
			}
			
			
		}
		}
		catch(Exception ex)
		{
			
		}
		return list;
	}
	 public String graph (String keyword,String year) {
		 	
		 	String result="";
		 	 String clientId = "Itd_qbaO_WVsSJhMflV4";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "DdxZIn0woW";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String apiURL = "https://openapi.naver.com/v1/datalab/search";
	            String body = "{\"startDate\":\""+year+"-01-01\",\"endDate\":\""+year+"-12-31\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\""+keyword+"\",\"keywords\":[\""+keyword+"\"]}]}";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            con.setRequestProperty("Content-Type", "application/json");

	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.write(body.getBytes());
	            wr.flush();
	            wr.close();

	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }

	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            //System.out.println(response.toString());
	            result=response.toString();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return result;
	    }
}
