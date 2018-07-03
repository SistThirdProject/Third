package com.sist.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAO {

	@Autowired
	private MongoTemplate mt;
	
	public void blogInsert(BlogVO vo)
	{
		mt.insert(vo,"keywords");
	}
	
}
