/*
* Author : Ketulkumar
* Created : 1 March 2020
**/


import java.util.*;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class TwitterHashtagStorm {
   public static void main(String[] args) throws Exception{
      String consumerKey = "###########################################";
      String consumerSecret = "###########################################";
		
      String accessToken = "###########################################";
      String accessTokenSecret = "###########################################";
		

      String[] keyWords = {"jazztastic","korona","foodie","SundayRoast"};
		
      Config config = new Config();
      config.setDebug(true);
		
      TopologyBuilder builder = new TopologyBuilder();
      builder.setSpout("twitter-spout", new TwitterSampleSpout(consumerKey,
         consumerSecret, accessToken, accessTokenSecret, keyWords));

      builder.setBolt("twitter-hashtag-reader-bolt", new HashtagReaderBolt())
         .shuffleGrouping("twitter-spout");

      builder.setBolt("twitter-hashtag-counter-bolt", new HashtagCounterBolt())
         .fieldsGrouping("twitter-hashtag-reader-bolt", new Fields("hashtag"));
			
      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology("TwitterHashtagStorm", config,
         builder.createTopology());
      Thread.sleep(10000);
      cluster.shutdown();
   }
}