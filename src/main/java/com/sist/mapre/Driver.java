package com.sist.mapre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Component;



@Component
public class Driver {
	
	@Autowired
	private Configuration conf;
	
	@Autowired
	private JobRunner jr;
	
	/*public void newsWord(String year)
	{
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		Driver air = (Driver) app.getBean("driver");
		
		air.hadoopFileDelete();
		
		air.copyFromLocal("2006.csv");
		
		air.JobCall();
		
		air.copyToLocal();
		
		System.out.println("End.....");
	}*/
	
	public void copyFromLocal(String file)
	{//파일 올리기
		try{
			
			FileSystem fs=FileSystem.get(conf);
			
			fs.copyFromLocalFile(new Path("/home/sist/news/"+file), new Path("/news_input_ns5/"+file));
			fs.close();
			
		}catch(Exception ex){}
		
	}
	
	public void JobCall()
	{//MapReduce 실행
		try{
			jr.call();
		}catch(Exception ex){}
	}
	
	public void copyToLocal()
	{//결과값 받아오기
		try{
			
			FileSystem fs=FileSystem.get(conf);
			fs.copyToLocalFile(new Path("/news_output_ns5/part-r-00000"), new Path("/home/sist/news/result"));
			fs.close();
		}catch(Exception ex){}
	}
	
	public void hadoopFileDelete()
	{
		try
		{
			FileSystem fs=FileSystem.get(conf);
			
			if(fs.exists(new Path("/news_input_ns5")))
				fs.delete(new Path("/news_input_ns5"),true);
			
			if(fs.exists(new Path("/news_output_ns5")))
				fs.delete(new Path("/news_output_ns5"),true);
			
		}catch(Exception ex)
		{}
	}
}
