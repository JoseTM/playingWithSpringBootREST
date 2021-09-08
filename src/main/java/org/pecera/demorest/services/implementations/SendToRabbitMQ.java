package org.pecera.demorest.services.implementations;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.pecera.demorest.services.SendToMessageQeue;
import org.springframework.stereotype.Service;

@Service
public class SendToRabbitMQ implements SendToMessageQeue {
    private final static String QUEUE_NAME = "hello";
    public static final String RABBITMQ_SERVER = "localhost";

    @Override
    public boolean send(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("sent -->" + message );
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean sendToQueue(String message, String queue) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish("", queue, null, message.getBytes());
            System.out.println("sent -->" + message + "to quewe " + queue);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean read() {
        ConnectionFactory factory  = new ConnectionFactory();
        factory.setHost(RABBITMQ_SERVER);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" read -> " + message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean readFromQueue(String queue) {
        ConnectionFactory factory  = new ConnectionFactory();
        factory.setHost(RABBITMQ_SERVER);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.queueDeclare(queue, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" read -> " + message);
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
