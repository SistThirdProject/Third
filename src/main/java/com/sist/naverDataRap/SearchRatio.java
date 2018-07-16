package com.sist.naverDataRap;
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
public class SearchRatio {

	
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
