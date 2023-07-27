package com.radhatechie.spring.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private static final String topic = "radhatechie";
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable  String name){

		template.send(topic,"Hi "+name +"Welcome to Kakfa Course");
		return "Data is published successfully";
	}

	@GetMapping("/publishJson")
	public String publishJsonMessage(){
		User user = new User(176,"Radhakrishna", new String[]{"Tampa Florida", "33647"});
		template.send(topic, user);
		return "JSON published to Kafka topic";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
