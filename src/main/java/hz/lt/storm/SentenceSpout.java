package hz.lt.storm;


import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;



public class SentenceSpout extends BaseRichSpout{

	private static final long serialVersionUID = 1L;

	private SpoutOutputCollector collector;
	
	private String[] sens={"my lover is LY","she is a beautiful girl"};
	
//	private int index =0;
	
	public void nextTuple() {
		// TODO Auto-generated method stub
		int index=(int)Math.round(Math.random());
		String[]val={sens[index]};
		this.collector.emit(new Values(val));
		Utils.sleep(500);
	}

	public void open(Map config, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector=collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("sentence"));
	}

}
