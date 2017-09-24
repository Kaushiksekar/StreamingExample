package org.gcp.twitterStreaming.Sample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import twitter4j.Status;

/**
 * Hello world!
 *
 */
public class StreamingSample 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	final String consumerKey="P6eLTUXZeaEMGkmyjhNKo41cE";
    	final String consumerSecretKey="qH0jpImfZxaKv9JYkSaxE4GwDeDBRmwyh1Gf30J9XIDA97ilx2";
    	final String accessToken="156294764-MAiWfabPP6DBhwnPnoB6tyOv0W5JryDXu6hLKlFO";
    	final String accessTokenSecret="lwrLad42qytfkUlDiGENE8gg3gMxprDXJ2sF13PsJVt2w";
    	
    	SparkConf conf=new SparkConf().setAppName("Twitter Streaming").setMaster("local[*]");
    	JavaStreamingContext sc=new JavaStreamingContext(conf,new Duration(3000));
    	
    	System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
    	System.setProperty("twitter4j.oauth.consumerSecret", consumerSecretKey);
    	System.setProperty("twitter4j.oauth.accessToken", accessToken);
    	System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);
    	
    	JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(sc);
    	
    	JavaDStream<String> statuses = twitterStream.map(
    			new Function<Status, String>() {

    				public String call(Status v1) throws Exception {
    					// TODO Auto-generated method stub
//    					if(v1.toString().toLowerCase().contains("#batmanday"))
//    						return v1.getText();
    					return v1.getText();
    				}
    				
    			}
    			);
    	
//    	JavaDStream<Status> statusAmerica = twitterStream.filter(
//    			new Function<Status, Boolean>() {
//    				
//    				@Override
//    				public Boolean call(Status v1) throws Exception {
//    					// TODO Auto-generated method stub
//    					return v1.getText().contains("e");
//    				}
//    			}
//    			);
    //	
//    	JavaDStream<String> statusAmericaRDD= statusAmerica.map(
//    			new Function<Status, String>() {
//    				@Override
//    				public String call(Status v1) throws Exception {
//    					// TODO Auto-generated method stub
//    					return v1.toString();
//    				}
//    			}
//    			);
    	
    	statuses.print();
    	sc.start();
    	sc.awaitTermination();
//    	sc.close();
    }
}
