package com.sist.graph;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.sist.json.UserVO;
public class NaverRat {

	    public String rat(UserVO vo) {
	        String clientId = "osTFswqVFp9R2cW_0dip";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "3Blg5EDBcw";//애플리케이션 클라이언트 시크릿값";
	        String ratio = "";

	         
	        try {
	            String apiURL = "https://openapi.naver.com/v1/datalab/search";
	            String body = "{\"startDate\":\""+vo.getSdate()+"\",\"endDate\":\""+vo.getEdate()+"\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\""+vo.getGname()+"\",\"keywords\":[\""+vo.getKeyword()+"\"]}]}";
	             
	          
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
//	            System.out.println(response.toString());
	            ratio = response.toString();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    
	    return ratio;
	}  
}
