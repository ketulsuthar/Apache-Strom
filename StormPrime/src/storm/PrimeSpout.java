/*
* Author : Ketulkumar
* Created : 12 Feb 2020
**/


package storm;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class PrimeSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;
	
	private int number = 1;

	/**
	* This method emit each integer number.
	*/
	@Override
	public void nextTuple() {
		
		collector.emit(new Values(new Integer(number++)));
		
	}

	/*
	*  This method do initialization, here we are initializing collctor object.
	*/
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector outputCollector) {
		
		this.collector = outputCollector;
		
	}

	/*
	* Declare the output field "number"
	*/
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("number"));
		
	}

}
