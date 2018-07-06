package com.sist.vo;

public class KeyWordSet {
	private int time;
	private KeyWordVO[] keywords = new KeyWordVO[10];
	public long getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public KeyWordVO[] getKeywords() {
		return keywords;
	}
	public void setKeywords(KeyWordVO[] keywords) {
		this.keywords = keywords;
	}
}
