package com.hadoopcoders;

import java.io.IOException;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.fs.Path;

public class WordCount {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		
		Job job = new Job(conf,"word count demo");
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path("/home/vijay/workspace/WordCountExample/input.txt"));
		FileOutputFormat.setOutputPath(job, new Path("/home/vijay/workspace/output/demo2"));
//		FileOutputFormat.setOutputPath(job, new Path("hdfs//localhost:100001/user/vijay/wordcount/demo2"));
		System.out.println("program started");
		job.waitForCompletion(true);

	}

}
