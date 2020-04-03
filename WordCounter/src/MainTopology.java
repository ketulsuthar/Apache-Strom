/*
* Author : Ketulkumar
* Created : 1 March 2020
**/

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class MainTopology {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReaderSpout());
		builder.setBolt("word-normalizer", new WordNormalizerBolt()).shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounterBolt(),4).fieldsGrouping("word-normalizer", new Fields("word"));
		//Configuration
		Config conf = new Config();
		conf.put("fileToRead", "/home/k2l/words.txt");
		conf.put("dirToWrite", "/home/k2l/wordcount/");
		conf.setDebug(true);
		
		LocalCluster cluster = new LocalCluster();
		
		try {
			cluster.submitTopology("Getting-Started-Toplogie", conf,builder.createTopology());
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cluster.shutdown();


	}

}
