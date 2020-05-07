package com.test.samples.mq;

import com.beust.jcommander.Parameter;

public class Args {
	@Parameter(names = "--help", description = "This usage help", help = true)
	private boolean help;
	
	@Parameter(names = {"-host", "-h"}, description = "MQ server host")
	private String host = "127.0.0.1";

	@Parameter(names = {"-port", "-p"}, description = "MQ server port")
	private Integer port = 1414;

	@Parameter(names = {"-channel", "-c"}, description = "Channel")
	private String channel = "DEV.ADMIN.SVRCONN";
	
	@Parameter(names = {"--queue_manager", "-qm"}, description = "Queue Manager")
	private String queueManager = "QM1";
	
	@Parameter(names = {"--username", "-u"}, description = "Username")
	private String username = "newrelic";
	
	@Parameter(names = {"--password", "-pw"}, description = "Password")
	private String password = "passw0rd";
	
	@Parameter(names = {"--queue", "-q"}, description = "Queue to put/get messages")
	private String queue = "DEV.QUEUE.1";
	
	@Parameter(names = {"--topic", "-t"}, description = "Topic to put/get messages")
	private String topic = "DEV.TOPIC.1";
	
	@Parameter(names = {"--message", "-m"}, description = "message to put")
	private String message = "a sample message from test producer. message id is " + System.currentTimeMillis() % 1000;

	@Parameter(names = {"--listen", "-l"}, description = "Set to false to put messages to queue. Set to true to get messages from queue", arity = 1)
	private boolean listen = false;
	
	@Parameter(names = {"--numMessages", "-n"}, description = "Number of messages to put or get")
	private Integer numMessages = 1;
	
	public boolean isHelp() {
		return help;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isListen() {
		return listen;
	}

	public void setListen(boolean listen) {
		this.listen = listen;
	}

	public Integer getNumMessages() {
		return numMessages;
	}

	public void setNumMessages(Integer numMessages) {
		this.numMessages = numMessages;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}

}