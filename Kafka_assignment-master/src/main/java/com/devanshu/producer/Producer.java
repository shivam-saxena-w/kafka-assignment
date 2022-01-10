package com.devanshu.producer;

import com.devanshu.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args){
        // For example 192.168.1.1:9092,192.168.1.2:9092


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.devanshu.serializer.UserSerializer");

        Random random=new Random();
        int minAge=18;
        int maxAge=28;
        int age;

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try{
            for(int i = 0; i < 10; i++){
                age= random.nextInt((maxAge-minAge)+1)+minAge;
                User user= new User(i,"Devanshu Verma",age,"BTech");
                System.out.println(user);
                kafkaProducer.send(new ProducerRecord("user",user));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}