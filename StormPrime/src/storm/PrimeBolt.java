/*
* Author : Ketulkumar
* Created : 12 Feb 2020
**/


package storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class PrimeBolt extends BaseRichBolt{
	
	private OutputCollector collector;


	/**
	* This method do called isPrime and print prime number.
	 */
	@Override
	public void execute(Tuple tuple) {
		
		int number = tuple.getInteger(0);
		
		if(isPrime(number)) {
			System.out.println(number);
		}
		collector.ack(tuple);
	}

	/*
	* This method check number is prime or not.
	* param int
	* return boolean
	*/
	private boolean isPrime(int number) {
		
		if(number == 1 || number == 2 || number == 3) {
			return true;
		}
		
		if(number%2 == 0) {
			return false;
		}
		
		for(int i=3; i*i<= number; i+=2) {
			if(number%i == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	*  This method do initialization, here we are initializing collctor object.
	*/
	@Override
	public void prepare(Map conf, TopologyContext topology, OutputCollector outputCollector) {
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
