package com.test.samples.mq;

import com.beust.jcommander.JCommander;
import com.test.samples.mq.queue.Consumer;
import com.test.samples.mq.queue.Producer;
import com.test.samples.mq.topic.Publish;
import com.test.samples.mq.topic.Subscribe;

public class App {

	public static void main(String[] argv) throws Exception {
		Args args = new Args();
		JCommander jct = JCommander.newBuilder().addObject(args).build();
		jct.parse(argv);
		if (args.isHelp()) {
			jct.usage();
			System.exit(0);
		}

		System.out.println("**********************");
		System.out.println("Connecting to: " + args.getHost() + ":" + args.getPort());
		System.out.println("Channel: " + args.getChannel());
		System.out.println("Queue Manager: " + args.getQueueManager());
		System.out.println("Username: " + args.getUsername());
		System.out.println("Password: " + args.getPassword());
		System.out.println("**********************");

		if (args.getMode().equalsIgnoreCase("produce")) {
			System.out.println("Starting Producer app...");
			Producer producer = new Producer(args);
			producer.setup();
			for (int i = 0; i < args.getNumMessages(); i++) {
				producer.putMessage();
			}
			producer.destroy();	
		} else if (args.getMode().equalsIgnoreCase("consume")) {
			System.out.println("Starting Consumer app listener...");
			Consumer consumer = new Consumer(args);
			consumer.setup();
			consumer.listenForMessages(args.getNumMessages());
			consumer.destroy();	
		} else if (args.getMode().equalsIgnoreCase("publish")) {
			System.out.println("Starting Topic publisher...");
			Publish publisher = new Publish(args);
			publisher.setup();
			for (int i = 0; i < args.getNumMessages(); i++) {
				publisher.putMessage();
			}
			publisher.destroy();	
		} else if (args.getMode().equalsIgnoreCase("subscribe")) {
			System.out.println("Starting Topic subscriber listener...");
			Subscribe subscriber = new Subscribe(args);
			subscriber.setup();
			subscriber.listenForMessages(args.getNumMessages());
			subscriber.destroy();	
		}
	}
}