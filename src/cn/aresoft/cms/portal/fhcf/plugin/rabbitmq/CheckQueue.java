package cn.aresoft.cms.portal.fhcf.plugin.rabbitmq;

import java.util.TimerTask;

import com.puff.log.Log;
import com.puff.log.LogFactory;


class CheckQueue extends TimerTask {
	private static boolean isRunning = true;
	private static Log log = LogFactory.get(CheckQueue.class);
			
	@Override
	public void run() {
		log.info("cllog: run() started");
		if (isRunning) {
			isRunning = false;
			recovery();
			isRunning = true;
		}
	}

	private void recovery() {
		log.info("cllog: recovery() started");
		if (PushMQ.getPushMQsing().isopen()) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			PushMQ.getPushMQsing().startRecovery();
		}
	}
	
	public static void main(String[] args) {
//		new CheckQueue();
//		boolean pushMessage = PushMQ.getPushMQsing().pushMessage(new TradeModel());
		new RabbitmqPlugin();
		log.info("cllog: main() finished");
	}

}
