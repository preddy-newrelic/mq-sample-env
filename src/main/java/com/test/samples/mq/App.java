package com.test.samples.mq;

import com.beust.jcommander.JCommander;
import com.test.samples.mq.queue.Consumer;
import com.test.samples.mq.queue.Producer;

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

		if (!args.isListen()) {
			System.out.println("Starting Producer app...");
			Producer producer = new Producer(args);
			producer.setup();
			for (int i = 0; i < args.getNumMessages(); i++) {
				producer.putMessage();
			}
			producer.destroy();	
		} else {
			System.out.println("Starting Consumer app listener...");
			Consumer consumer = new Consumer(args);
			consumer.setup();
			consumer.listenForMessages(args.getNumMessages());
			consumer.destroy();	
		}
	}
}