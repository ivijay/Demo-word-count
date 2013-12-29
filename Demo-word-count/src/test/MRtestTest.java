package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.hadoopcoders.*;
 
public class MRtestTest {
 
  MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
  MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
 
  @Before
  public void setUp() {
    WordCountMapper mapper = new WordCountMapper();
    WordCountReducer reducer = new WordCountReducer();
    mapDriver = MapDriver.newMapDriver(mapper);;
    reduceDriver = ReduceDriver.newReduceDriver(reducer);
    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
  }
 
  @Test
  public void testMapper() throws IOException {
    mapDriver.withInput(new LongWritable(), new Text(
        "This is a test for test"));
    mapDriver.withOutput(new Text("This"), new IntWritable(1));
    mapDriver.withOutput(new Text("is"), new IntWritable(1));
    mapDriver.withOutput(new Text("a"), new IntWritable(1));
    mapDriver.withOutput(new Text("test"), new IntWritable(1));
    mapDriver.withOutput(new Text("for"), new IntWritable(1));
    mapDriver.withOutput(new Text("test"), new IntWritable(1));
    mapDriver.runTest();
  }
 
  @Test
  public void testReducer() throws IOException {
    List<IntWritable> values = new ArrayList<IntWritable>();
    values.add(new IntWritable(1));
    values.add(new IntWritable(1));
    reduceDriver.withInput(new Text("test"), values);
//    reduceDriver.withOutput(new Text("This"),new IntWritable(1));
    reduceDriver.withOutput(new Text("test"),new IntWritable(2));
    reduceDriver.runTest();
  }
}