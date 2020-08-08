package com.top.shop.user.broker.listener;

import com.top.shop.user.broker.message.DummyBrokerMessageOne;
import com.top.shop.user.broker.message.DummyBrokerMessageTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = "t.chassistwo")
public class DummyListener {

	private static final Logger LOG = LoggerFactory.getLogger(DummyListener.class);

	@KafkaHandler
	public void handleMessageOne(DummyBrokerMessageOne message) {
		LOG.info(message.toString());
	}

	@KafkaHandler
	public void handleMessageTwo(DummyBrokerMessageTwo message) {
		LOG.info(message.toString());
	}

}
