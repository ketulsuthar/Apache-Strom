/*
* Author : Ketulkumar
* Created : 1 March 2020
**/

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class WordCounterBolt extends BaseBasicBolt {
	
	private static final long serialVersionUID = 1L;
	Integer id;
	String name;
	Map<String, Integer> counters;
	String fileName;
	
	@Override
	public void prepare(Map conf, TopologyContext context) {
		
		this.counters = new HashMap<String, Integer>();
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
		this.fileName = conf.get("dirToWrite").toString()+
				"output"+"-"
				+context.getThisTaskId()
				+"-"+context.getThisComponentId()+".txt";
		
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		if(!counters.containsKey(str)){
			counters.put(str, 1);
		}else{
			Integer c = counters.get(str) + 1;
			counters.put(str, c);
		}
	}
	
	public void cleanup() {
		try {
			PrintWriter writer = new PrintWriter(fileName,"UTF-8");
			for(Map.Entry<String,Integer> entry : counters.entrySet()) {
				writer.println(entry.getKey()+":"+entry.getValue());
			}
			writer.close();
		}catch(Exception e) {}
	}

}
