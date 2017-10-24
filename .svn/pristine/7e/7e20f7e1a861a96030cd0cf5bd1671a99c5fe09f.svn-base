package cn.aresoft.cms.portal.fhcf.plugin.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puff.framework.utils.SerializeUtil;
import com.puff.log.Log;
import com.puff.log.LogFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 
 * @ClassName:Work
 * @Description:发送端
 * @author ZJY
 * @date:2015年10月21日 上午11:29:03
 */
public class PushMQ {
	private static Log log = LogFactory.get(PushMQ.class);
	private Connection connection;
	private Channel channel;
	private String host;
	private int port;
	private String queueName;
	private String user;
	private String passwd;

	private PushMQ() {
		log.info("cllog: PushMQ() started");
	}

	private static class PushMQsing {
		static final PushMQ INERA = new PushMQ();
	}

	public static PushMQ getPushMQsing() {
		log.info("cllog: getPushMQsing() started");
		return PushMQsing.INERA;
	}

	public void init() {
		log.info("cllog: init() started");
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(user);
		factory.setPassword(passwd);
		factory.setAutomaticRecoveryEnabled(true);
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			// 声明队列
			boolean durable = true;// 1、设置队列持久化
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-cancel-on-ha-failover", true);
			channel.queueDeclare(queueName, durable, false, false, args);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public boolean pushMessage(TradeModel trademodel) {
//		log.info("cllog: pushMessage() started");
//		try {
//			if (isopen()) {
//				byte[] fstserialize = SerializeUtil.fstserialize(trademodel);
//				channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, fstserialize);
//				return true;
//			}
//			return false;
//		} catch (ShutdownSignalException | ConsumerCancelledException | IOException e) {
//			return false;
//		}
//	}

	void startRecovery() {
		boolean whileboolean = false;
		while (!whileboolean) {//如果队列服务没开，一直执行循环直到队列服务开启
			if (isopen()) {
				close();
				whileboolean = true;
				init();
			} else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void close() {
		log.info("cllog: close() started");
		if (channel != null) {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isopen() {
		log.info("cllog: isopen() started");
		if (connection == null) {
			return false;
		}
		return connection.isOpen();
	}

	void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
