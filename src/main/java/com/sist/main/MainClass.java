package com.sist.main;

import com.sist.crawling.Manager;

public class MainClass {
	public static void main(String[] args) {
		/*NaverOpenAPI.getXml("�Ϻ� ���װ�");
		System.out.println();*/
		Manager manager = new Manager();
		String[] items = {"축구","한국","러시아","브라질"};
		manager.crwNews(items);
	}
}
