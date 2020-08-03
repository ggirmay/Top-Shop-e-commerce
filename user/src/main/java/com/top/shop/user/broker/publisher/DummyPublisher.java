package com.top.shop.user.broker.publisher;

import com.top.shop.user.broker.message.DummyBrokerMessageOne;
import com.top.shop.user.broker.message.DummyBrokerMessageTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class DummyPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishMessageOne(DummyBrokerMessageOne message) {
		kafkaTemplate.send("t.chassistwo", message);
	}

	public void publishMessageTwo(DummyBrokerMessageTwo message) {
		kafkaTemplate.send("t.chassistwo", message);
	}

}
