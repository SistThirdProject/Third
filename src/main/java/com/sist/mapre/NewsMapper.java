package com.sist.mapre;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class NewsMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	private final IntWritable ONE = new IntWritable(1);
	private Text result= new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		if(key.get()>0)
		{
			String[] columns=value.toString().split(",");
			//System.out.println(columns[4]);
			columns[3]=columns[3].replaceAll("\"", "");
			String[] topicKwd=columns[3].split(",");
			String[] topic=columns[2].split(" ");
			
			if(columns!=null && columns.length>0)
			{
				try{
					for(int i=0;i<topicKwd.length;i++)
					{
						//System.out.println(topicKwd[i]);
						result.set(topicKwd[i]);
						context.write(result, ONE);
					}
					
					for(int i=0;i<topic.length;i++)
					{
						result.set(topic[i]);
						context.write(result, ONE);
					}
						
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		}

	}
	
	
}
