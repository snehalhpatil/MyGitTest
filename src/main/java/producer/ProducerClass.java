package producer;


import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import kafka.producer.ProducerConfig;

public class ProducerClass {
	
	 static ProducerConfig config;
	
	public ProducerClass()
	{
		 Properties props = new Properties();
	        props.put("metadata.broker.list", "localhost:9092");
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        props.put("request.required.acks", "1");
	 
	       config = new ProducerConfig(props);
	}
	
	public void testProducer() 
	{
		int num_events = 10000;
        Random randomNumber = new Random();	       
 
        Producer<String, String> producer = new Producer<String, String>(config);
 
        for (int nEvents = 0; nEvents < num_events; nEvents++) { 
               String fromIP = "172.68.56." + randomNumber.nextInt(255); 
               String msg = "Test Message-" + nEvents; 
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("Test", fromIP, msg);
               producer.send(data);
        }
        producer.close();
	}
	  
	public static void main(String[] args) 
	{
		ProducerClass producer = new ProducerClass();
		producer.testProducer();
	}
}

