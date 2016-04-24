package storm.analytics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 。它负责持续追踪每个用户浏览过的产品，并决定应当增加计数的键值对
 */
public class UserHistoryBolt extends BaseRichBolt{
	

	private static final long serialVersionUID = 1L;
	
	OutputCollector collector;
	String host;
	int port;
	Jedis jedis;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
		this.host = (String)stormConf.get("redis-host");
		this.port = Integer.valueOf(stormConf.get("redis-port").toString());
		reconnect();
	}

	public void reconnect() {
		this.jedis = new Jedis(host, port);
	}

	HashMap<String, Set<String>> usersNavigatedItems = new HashMap<String, Set<String>>(); 
	
	
	
	@Override
	public void execute(Tuple input) {
		String user = input.getString(0);
		String prod1 = input.getString(1);
		String cat1 = input.getString(2);

		// Product key will have category information embedded.
		// 产品键嵌入了产品类别信息
		String prodKey = prod1+":"+cat1;
		
		Set<String> productsNavigated = getUserNavigationHistory(user);
		
		// If the user previously navigated this item -> ignore it
		//如果用户以前浏览过->忽略它
		if(!productsNavigated.contains(prodKey)) {
			
			// Otherwise update related items
			//否则更新相关条目
			for (String other : productsNavigated) {
				String [] ot = other.split(":");
				String prod2 = ot[0];
				String cat2 = ot[1]; 
				collector.emit(new Values(prod1, cat2));
				collector.emit(new Values(prod2, cat1));
			}
			addProductToHistory(user, prodKey);
		}
	}

	private void addProductToHistory(String user, String product) {
		Set<String> userHistory = getUserNavigationHistory(user);
		userHistory.add(product);
		jedis.sadd(buildKey(user), product);
	}

	//返回用户浏览过的产品集
	private Set<String> getUserNavigationHistory(String user) {
		//从本地获取，如果获取不到，从redis服务器获取，并添加到本地
		Set<String> userHistory = usersNavigatedItems.get(user);
		if(userHistory == null) {
			userHistory = jedis.smembers(buildKey(user));
			if(userHistory == null) 
				userHistory = new HashSet<String>();
			usersNavigatedItems.put(user, userHistory);
		}
		return userHistory;
	}

	private String buildKey(String user) {
		return "history:"+user;
	}


	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("product", "categ"));
	}
}
