package hz.lt.storm;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;



public class SentenceSpout extends BaseRichSpout{

	private static final long serialVersionUID = 1L;

	private ConcurrentHashMap<UUID, Values> pending;
	
	private SpoutOutputCollector collector;
	
	private String[] sens={"my lover is LY","she is a beautiful girl"};
	
//	private int index =0;
	
	public void nextTuple() {
		// TODO Auto-generated method stub
		int index=(int)Math.round(Math.random());
		Values values=new Values(sens[index]);
		UUID msgId=UUID.randomUUID();
		this.pending.put(msgId, values);
		this.collector.emit(values,msgId);
		Utils.sleep(500);
	}

	public void open(Map config, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector=collector;
		this.pending=new ConcurrentHashMap<UUID, Values>();
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("sentence"));
	}

	
	public void ack(Object msgId){
		
		this.pending.remove(msgId);
	}
	
	public void fail(Object msgId){
		
		this.collector.emit(this.pending.get(msgId),msgId);
	}
}
