package com.sist.main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NaverOpenAPI {
	public static String getXml(String keyword) {
		String clientId = "NwD7JYKKWJhQ0Axef3dS";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
		String clientSecret = "cjjALygkAj";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";
		String data="";
		try {
			String text = URLEncoder.encode(keyword, "UTF-8");
			// String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text; //
			// json ���
			String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query=" + text;
			// // xml ���
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // ���� �߻�
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			data = response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return data;
	}
}
