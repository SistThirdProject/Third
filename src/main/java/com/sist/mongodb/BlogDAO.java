package com.sist.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.sist.board.BoardVO;
import com.sist.vo.NewsVO;

@Repository
public class BlogDAO {

	
	@Autowired
	private MongoTemplate mt;
	
	public void blogInsert(BlogVO vo)
	{
		mt.insert(vo,"keywords");
	}
	
	public List<BlogVO> getBlogData(String keyword)
	{
		BasicQuery query=new BasicQuery("{keyword:\""+keyword+"\"}");
		List<BlogVO> list=mt.find(query, BlogVO.class,"blog");
		
		/*for(BlogVO vo:list)
		{
			System.out.println(vo.getKeyword());
			System.out.println(vo.getData());
		}*/
		
		return list;
	}
	
	public List<NewsVO> getNewsData(String keyword)
	{
		BasicQuery query=new BasicQuery("{keyword:\""+keyword+"\"}");
		List<NewsVO> list=mt.find(query, NewsVO.class,"news");
		
		/*for(BlogVO vo:list)
		{
			System.out.println(vo.getKeyword());
			System.out.println(vo.getData());
		}*/
		
		return list;
	}
	
}
