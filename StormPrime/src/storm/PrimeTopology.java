package storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class PrimeTopology {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("spout", new PrimeSpout());
		
		builder.setBolt("bolt", new PrimeBolt()).shuffleGrouping("spout");
		
		Config conf = new Config();
		
		LocalCluster cluster = new LocalCluster();
		
		cluster.submitTopology("MyTopology", conf, builder.createTopology());
		
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cluster.killTopology("MyTopology");

		cluster.shutdown();
	}

}
