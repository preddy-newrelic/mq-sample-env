package com.test.samples.mq.topic;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.test.samples.mq.Args;

public class Subscribe {

	private JMSContext context = null;
	private Topic topic = null;
	private JMSConsumer subscriber = null;
	private Args args = null;
	
	public Subscribe(Args args) {
		super();
		this.args = args;
	}

	public void setup() throws Exception {

		JmsFactoryFactory jmsFactoryFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
		JmsConnectionFactory jmsConnectionFactory = jmsFactoryFactory.createConnectionFactory();

		jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, args.getHost());
		jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, args.getPort());
		jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, args.getChannel());
		jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, args.getQueueManager());
		jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "MQ Sample Producer");
		jmsConnectionFactory.setStringProperty(WMQConstants.USERID, args.getUsername());
		jmsConnectionFactory.setStringProperty(WMQConstants.PASSWORD, args.getPassword());

		context = jmsConnectionFactory.createContext();
		System.out.println("Accessing topic: " + args.getTopic());
		topic = context.createTopic("topic:///" + args.getTopic());
		subscriber = context.createSharedConsumer(topic, "Sample Subscriber");
	}

	public void destroy() {
		context.close();
	}

	public void listenForMessages(Integer numMessages) {
		int i = 0;
		boolean keepRunning = true;
		while (keepRunning ) {
			try {
				String receivedMessage = subscriber.receiveBody(String.class); // blocks for a message
				if (receivedMessage != null) {
					System.out.println("\nReceived message:\n" + receivedMessage);
				}
				Thread.sleep(250);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
			if (i >= numMessages) {
				keepRunning = false;
			}
		}
	}

}
