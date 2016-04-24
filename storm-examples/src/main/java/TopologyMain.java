import backtype.storm.StormSubmitter;
import bolts.Print;
import spouts.WordReader;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import bolts.WordCounter;
import bolts.WordNormalizer;

/**
 * mvn exec:java -Dexec.mainClass="TopologyMain"
 * -Dexec.args=”src/main/resources/words.txt。
 */
public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
         
        //Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer())
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(),2)
			.fieldsGrouping("word-normalizer", new Fields("word"));
		builder.setBolt("print",new Print(),1).shuffleGrouping("word-counter");
		
        //Configuration拓扑配置的Config对象
		//它会在运行时与集群配置合并，并通过prepare方法发送给所有节点。
		Config conf = new Config();
//		conf.put("wordsFile", args[0]);
		String filePath = TopologyMain.class.getResource("words.txt").getPath();
		conf.put("wordsFile", filePath);
//		conf.put("storm.zookeeper.servers","192.168.217.100");
//		conf.put("storm.zookeeper.port",2181);
		conf.setDebug(false);

        //Topology run运行拓扑
//		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		conf.setMaxTaskParallelism(1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		Thread.sleep(1000);
//		cluster.shutdown();
	}
}
