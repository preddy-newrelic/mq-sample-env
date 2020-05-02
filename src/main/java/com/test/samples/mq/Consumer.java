package com.test.samples.mq;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class Consumer {

	private JMSContext context = null;
	private Destination destination = null;
	private JMSConsumer consumer = null;
	private Args args = null;

	public Consumer(Args args) {
		super();
		this.args  = args;
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
		System.out.println("Accessing queue: " + args.getQueue());
		destination = context.createQueue("queue:///" + args.getQueue());
		consumer = context.createConsumer(destination);
	}

	public void destroy() {
		context.close();
	}

	public void listenForMessages(Integer numMessages) {
		int i = 0;
		boolean keepRunning = true;
		while (keepRunning ) {
			try {
				String receivedMessage = consumer.receiveBody(String.class); // blocks for a message
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
