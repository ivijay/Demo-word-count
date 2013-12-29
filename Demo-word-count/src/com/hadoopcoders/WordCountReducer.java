package com.hadoopcoders;

import java.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
	
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		System.out.println("in reducer class");
		int total = 0;
		for(IntWritable i: values) {
			
			total = i.get();
			
		}
		
		context.write(key,new IntWritable(total));
		
	}

}
