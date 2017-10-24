package cn.aresoft.cms.portal.fhcf.plugin.rabbitmq;

import java.util.Properties;
import java.util.Timer;

import com.puff.framework.utils.StringUtil;
import com.puff.log.Log;
import com.puff.log.LogFactory;
import com.puff.plugin.Plugin;

/**
 * 
 * @ClassName:RabbitmqPlugin
 * @Description:队列插件
 * @author ZJY
 * @date:2015年10月26日 下午6:52:02
 */
public class RabbitmqPlugin implements Plugin {
	private static Log log = LogFactory.get(RabbitmqPlugin.class);
	private String queueHost;
	private String queueName;
	private int port;
	private String user;
	private String passwd;

	@Override
	public void init(Properties prop) {
		log.info("cllog: init()");
		queueName = prop.getProperty("Name", "TradeQueue");
		queueHost = prop.getProperty("Host", "localhost");
		user = prop.getProperty("user", "");
		passwd = prop.getProperty("passwd", "");
		port = Integer.parseInt(prop.getProperty("port", "5672"));
		if (StringUtil.blank(queueHost)) {
			log.warn("RabbitmqPlugin  init  host is null");
		}
	}

	@Override
	public boolean start() {//框架启动的时候会调用这个方法
		log.info("cllog: start()");
		if (!PushMQ.getPushMQsing().isopen()) {
			//如果队列服务没开，把队列的参数设置一遍，以使其顺利开启。
			
			PushMQ.getPushMQsing().setHost(queueHost);
			PushMQ.getPushMQsing().setQueueName(queueName);
			PushMQ.getPushMQsing().setUser(user);
			PushMQ.getPushMQsing().setPasswd(passwd);
			PushMQ.getPushMQsing().setPort(port);
			PushMQ.getPushMQsing().init();
		}
		Timer saveMsg = new Timer("Task-CheckPushStatus");
		saveMsg.schedule(new CheckQueue(), 0, 1000 * 10);//开启一个线程，每10秒执行CheckQueue的run()
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

}
