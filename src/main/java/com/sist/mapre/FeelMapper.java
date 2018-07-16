package com.sist.mapre;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class FeelMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	private final IntWritable ONE = new IntWritable(1);
	private Text result= new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
			String[] feeling=value.toString().split(",");
			
			
			if(feeling!=null && feeling.length>0)
			{
				try{
					for(int i=0;i<feeling.length;i++)
					{
						System.out.println(feeling[i]);
						result.set(feeling[i]);
						context.write(result, ONE);
					}
					
						
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		}

	}
	
	

