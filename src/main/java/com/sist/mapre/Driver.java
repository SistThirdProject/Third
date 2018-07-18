package com.sist.mapre;

import javax.annotation.Resource;

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

	public void copyFromLocal(String file)
	{//파일 올리기
		try{
			
			FileSystem fs=FileSystem.get(conf);
			
			fs.copyFromLocalFile(new Path("/home/sist/thdata/"+file), new Path("/input_ns5/"+file));
			fs.close();
			
		}catch(Exception ex){System.out.println(ex.getMessage());}
		
	}
	
	public void JobCall(JobRunner jr)
	{//MapReduce 실행
		try{
			jr.call();
		}catch(Exception ex){System.out.println(ex.getMessage());}
	}
	
	public void copyToLocal()
	{//결과값 받아오기
		try{
			
			FileSystem fs=FileSystem.get(conf);
			fs.copyToLocalFile(new Path("/output_ns5/part-r-00000"), new Path("/home/sist/thdata/result"));
			fs.close();
		}catch(Exception ex){System.out.println(ex.getMessage());}
	}
	
	public void hadoopFileDelete()
	{
		try
		{
			FileSystem fs=FileSystem.get(conf);
			
			if(fs.exists(new Path("/input_ns5")))
				fs.delete(new Path("/input_ns5"),true);
			
			if(fs.exists(new Path("/output_ns5")))
				fs.delete(new Path("/output_ns5"),true);
			
		}catch(Exception ex)
		{System.out.println(ex.getMessage());}
	}
}
