package org.pecera.demorest.services.implementations;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.pecera.demorest.services.SendToMessageQeue;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaService implements SendToMessageQeue {

    @Override
    public boolean send(String message) {
        Producer producer = getProducer();
        ProducerRecord<String, String> record = getStringStringProducerRecord(message);
        try{
            producer.send(record).get();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            return false;
        }
    }

    private ProducerRecord<String, String> getStringStringProducerRecord(String message) {
        ProducerRecord<String, String> record =
                new ProducerRecord<>("quickstart-events", "precision products", message);
        return record;
    }

    private Producer getProducer() {

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer producer = new KafkaProducer<String, String>(kafkaProps);
        return producer;
    }

    @Override
    public boolean read() {
        return false;
    }
}
