/**
 * Word count example demo.
 */
package com.hadoopcoders;

import java.io.*;
import java.util.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

/**
 * @author vijay
 *
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
	
	private final IntWritable one = new IntWritable(1);
	
	private final Text word = new Text();
	
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
		
		System.out.println("in mapper class");
		String line = value.toString();
		
		StringTokenizer token = new StringTokenizer(line);
		
		while(token.hasMoreTokens()) {
			
			word.set(token.nextToken());
			
			context.write(word, one);
			
		}
		
		
		
		
	}

}
