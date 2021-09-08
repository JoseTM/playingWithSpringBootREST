package org.pecera.demorest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pecera.demorest.services.implementations.SendToRabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTests {

    @Autowired
    private SendToRabbitMQ sendToMessageQeue;

    @Test
    public void whenMessageIsSended_isCorrectSended(){
        Assertions.assertTrue(sendToMessageQeue.send("hola poroto"));
    }

    @Test
    public  void readMessageFromRabbitQueue(){
        Assertions.assertTrue(sendToMessageQeue.read());
    }

    @Test
    public void whenMessageIsSended_isCorrectSendedToQueue(){
        Assertions.assertTrue(sendToMessageQeue.sendToQueue("hola poroto", "micolaTest"));
    }

    @Test
    public  void readMessageFromEspecificRabbitQueue(){
        Assertions.assertTrue(sendToMessageQeue.readFromQueue("micolaTest"));
    }
}
