package com.test.samples.mq.topic;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.test.samples.mq.Args;

public class Publish {

	private JMSContext context = null;
	private Destination destination = null;
	private JMSProducer producer = null;
	private Args args = null;

	public Publish(Args args) {
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
		jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "MQ Sample Topic Publisher");
		jmsConnectionFactory.setStringProperty(WMQConstants.USERID, args.getUsername());
		jmsConnectionFactory.setStringProperty(WMQConstants.PASSWORD, args.getPassword());

		context = jmsConnectionFactory.createContext();
		System.out.println("Accessing topic: " + args.getTopic());
		destination = context.createTopic("topic:///" + args.getTopic());
		producer = context.createProducer();
	}

	public void destroy() {
		context.close();
	}

	public void putMessage() {
		System.out.println("Putting message on Topic: " + args.getTopic());
		TextMessage message = context
				.createTextMessage("Your T number  is " + System.currentTimeMillis() % 1000);
		producer.send(destination, message);
		System.out.println("\nSent message to topic:" + message);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}

}
