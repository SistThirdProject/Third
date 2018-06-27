package com.sist.crawling;

import java.util.*;

import com.sist.vo.*;
import com.sist.xml.Item;

public class Manager {
	public List<NewsVO> crwNews(String[] items) {
		List<NewsVO> volist = new ArrayList<NewsVO>();
		List<Item> itemlist = new ArrayList<Item>();
		NewsLinkCRW[] linkcrw = new NewsLinkCRW[items.length];
		List<NewsDataCRW> crwlist = new ArrayList<NewsDataCRW>();
		// rss���� �� item�� ���� ���� ��ũ ������
		for (int i = 0; i < items.length; i++) {
			linkcrw[i] = new NewsLinkCRW(items[i]);
			linkcrw[i].start();
		}
		
		NewsDataCRW crw;
		for (int i = 0; i < items.length; i++) {
			while (linkcrw[i].isAlive())
				;
			for (int j = 0; j < linkcrw[i].getList().size(); j++) {
				crw = new NewsDataCRW(linkcrw[i].getList().get(j));
				crw.start();
				crwlist.add(crw);
			}
		}
		
		for(NewsDataCRW c: crwlist) {
			while(c.isAlive());
			volist.add(c.getVo());
			itemlist.add(c.getItem());
		}

		return volist;
	}
}
