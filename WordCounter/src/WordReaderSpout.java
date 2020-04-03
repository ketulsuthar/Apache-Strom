/*
* Author : Ketulkumar
* Created : 1 March 2020
**/



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class WordReaderSpout extends BaseRichSpout  {

	private SpoutOutputCollector collector;
	private FileReader fileReader;
	private boolean completed = false;
	private TopologyContext context;
	private String str;
	private BufferedReader reader;
	public  boolean isDistributed() {return false;}
	
	@Override
	public void ack(Object msgId) {
		System.out.println("OK:"+msgId);
		
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("FAIL:"+msgId);
		
	}

	@Override
	public void nextTuple() {
		if(!completed){
			try {
				this.str = reader.readLine();
				if (this.str != null) {
					this.collector.emit(new Values(str),str);
					
				}else {
					completed = true;
					fileReader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("Error reading tuple",e);
			}
		}
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.context = context;
			this.fileReader = new FileReader(conf.get("fileToRead").toString());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file["+conf.get("fileToRead")+"]");
		}
		this.collector = collector;
		this.reader = new BufferedReader(fileReader);
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("line"));
		
	}
	
}
