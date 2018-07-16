package com.sist.feel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MainClass {
	
	@Autowired
	private MongoTemplate mt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void getData(String keyword)
	{
		
	}

}
