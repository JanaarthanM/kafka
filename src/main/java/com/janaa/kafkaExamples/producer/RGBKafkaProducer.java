package com.janaa.kafkaExamples.producer;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class RGBKafkaProducer {

	public static void main(String[] args) throws Exception {
		PropertiesClass propertiesClass = new PropertiesClass();
		Properties props = propertiesClass.getProducerProperties();
		int[] partitions = { 0, 1, 2 };
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 1; i <= 50; i++) {
			Random random = new Random();
			int part = partitions[random.nextInt(partitions.length)];
			String value = "";
			if (part == 0) {
				value = "red";
			} else if (part == 1) {
				value = "green";
			} else if (part == 2) {
				value = "blue";
			}
			ProducerRecord<String, String> record = new ProducerRecord<>("consumer1", part, String.valueOf(part),
					value);
			Thread.sleep(500);
			producer.send(record);
			System.out.println("Partition = " + part + ", Key = " + part + ", value = " + value);
		}
		System.out.println("Message sent successfully");
		producer.close();
	}

}
